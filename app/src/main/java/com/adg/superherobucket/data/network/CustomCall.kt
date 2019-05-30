package com.adg.superherobucket.data.network

import arrow.core.Either
import com.adg.superherobucket.common.BaseError
import com.adg.superherobucket.common.BaseError.NetworkError
import com.adg.superherobucket.common.BaseError.NetworkError.Code.*
import com.adg.superherobucket.data.network.model.BaseResponse
import com.adg.superherobucket.data.network.model.DEFAULT_RESPONSE_RESULT_SUCESS
import com.adg.superherobucket.data.network.model.UNAUTHORIZED_RESPONSE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class CustomCall<R : BaseResponse>(private val call: Call<R>) {

    fun run(): Either<BaseError, R> {
        // run in the same thread
        return try {
            // call and handle response
            handleResponse(call.execute())

        } catch (t: IOException) {
            Either.left(
                NetworkError(
                    NETWORK_IO_EXCEPTION.code,
                    t.message ?: "IOException"
                )
            )
        }
    }

    fun process(responseHandler: (R?, BaseError?) -> Unit) {
        // define callback
        val callback = object : Callback<R> {

            override fun onFailure(call: Call<R>?, t: Throwable?) =
                responseHandler(
                    null,
                    NetworkError(
                        NETWORK_IO_EXCEPTION.code,
                        t?.message ?: "IOException"
                    )
                )

            override fun onResponse(call: Call<R>?, r: Response<R>?) {
                handleResponse(r).fold(
                    {
                        responseHandler(null, it)
                    },
                    {
                        responseHandler(it, null)
                    }
                )
            }
        }

        // enqueue network call
        call.enqueue(callback)
    }

    private fun handleResponse(response: Response<R>?): Either<BaseError, R> {
        if (response?.isSuccessful == true) {

            val serialized = response.body()

            if (serialized?.response == DEFAULT_RESPONSE_RESULT_SUCESS) {
                return Either.right(serialized)
            } else {
                return Either.left(
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
            return Either.left(
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
}

