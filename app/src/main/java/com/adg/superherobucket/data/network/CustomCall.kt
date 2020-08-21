package com.adg.superherobucket.data.network

import arrow.core.Either
import com.adg.superherobucket.common.BaseError.NetworkError
import com.adg.superherobucket.common.BaseError.NetworkError.Code.*
import com.adg.superherobucket.data.BaseEither
import com.adg.superherobucket.data.network.model.BaseResponse
import com.adg.superherobucket.data.network.model.DEFAULT_RESPONSE_RESULT_SUCESS
import com.adg.superherobucket.data.network.model.UNAUTHORIZED_RESPONSE
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class CustomCall<R : BaseResponse>(
    private val delegate: Call<R>,
) : Call<BaseEither<R>> {

    override fun enqueue(callback: Callback<BaseEither<R>>) =
        delegate.enqueue(object : Callback<R> {
            override fun onFailure(call: Call<R>, t: Throwable) {
                callback.onResponse(
                    this@CustomCall,
                    Response.success(t.process())
                )
            }

            override fun onResponse(call: Call<R>, response: Response<R>) {
                callback.onResponse(
                    this@CustomCall,
                    Response.success(handleResponse(response))
                )

            }
        })

    override fun execute(): Response<BaseEither<R>> =
        Response.success(
            try {
                // call and handle response
                handleResponse(delegate.execute())
            } catch (t: IOException) {
                Either.Left(
                    NetworkError(
                        NETWORK_IO_EXCEPTION.code,
                        t.message ?: "IOException"
                    )
                )
            }
        )

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = CustomCall(delegate.clone())

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

    private fun handleResponse(response: Response<R>?): BaseEither<R> {
        if (response?.isSuccessful == true) {

            val serialized = response.body()

            if (serialized?.response == DEFAULT_RESPONSE_RESULT_SUCESS) {
                return Either.Right(serialized)
            } else {
                return Either.Left(
                    if (serialized?.error == UNAUTHORIZED_RESPONSE) {
                        NetworkError(
                            NETWORK_UNAUTHORIZED.code,
                            serialized.error
                        )
                    } else {
                        NetworkError(
                            NETWORK_DEFAULT_ERROR.code,
                            serialized?.error ?: ""
                        )
                    }
                )
            }

        } else {
            return Either.Left(
                if (response?.code() in 400..511)
                    NetworkError(
                        NETWORK_SERVER_ERROR.code,
                        "${response?.code()} - ${response?.raw()}"
                    )
                else NetworkError(
                    NETWORK_DEFAULT_ERROR.code,
                    "Not successful"
                )
            )
        }
    }

    private fun Throwable.process() = Either.Left(
        NetworkError(
            id = NETWORK_IO_EXCEPTION.code,
            description = this.message ?: "IOException"
        )
    )
}

