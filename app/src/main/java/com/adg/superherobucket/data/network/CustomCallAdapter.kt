package com.adg.superherobucket.data.network

import arrow.core.Either
import com.adg.superherobucket.data.BaseEither
import com.adg.superherobucket.data.network.model.BaseResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class CustomCallAdapter<R : BaseResponse>(private val responseType: Type) :
    CallAdapter<R, Call<BaseEither<R>>> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): Call<BaseEither<R>> = CustomCall(call)

}

class CustomCallAdapterFactory private constructor() : CallAdapter.Factory() {

    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        // suspend functions wrap the response type in `Call`
        if (Call::class.java != getRawType(returnType)) {
            return null
        }

        // check first that the return type is `ParameterizedType`
        check(returnType is ParameterizedType) {
            "return type must be parameterized as CustomCall"
        }

        // get the response type inside the `Call` type
        val responseType = getParameterUpperBound(0, returnType)
        // if the response type is not ApiResponse then we can't handle this type, so we return null
        if (getRawType(responseType) != Either::class.java) {
            return null
        }

        // the response type is ApiResponse and should be parameterized
        check(responseType is ParameterizedType) { "Response must be parameterized as Either" }
        val successBodyType = getParameterUpperBound(1, responseType)

        return CustomCallAdapter<BaseResponse>(successBodyType)

    }

    companion object {
        @JvmStatic
        fun create() = CustomCallAdapterFactory()
    }

}