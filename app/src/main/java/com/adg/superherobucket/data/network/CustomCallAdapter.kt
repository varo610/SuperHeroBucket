package com.adg.superherobucket.data.network

import com.adg.superherobucket.data.network.model.BaseResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class CustomCallAdapter<R:BaseResponse>(private val responseType: Type): CallAdapter<R, Any> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): Any = CustomCall(call)

}

class CustomCallAdapterFactory private constructor() : CallAdapter.Factory() {

    override fun get(returnType: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): CallAdapter<*, *>? {
        return returnType?.let {
            return try {
                // get enclosing type
                val enclosingType = (it as ParameterizedType)

                // ensure enclosing type is 'CustomCall'
                if (enclosingType.rawType != CustomCall::class.java)
                    null
                else {
                    val type = enclosingType.actualTypeArguments[0]
                    CustomCallAdapter<BaseResponse>(type)
                }
            } catch (ex: ClassCastException) {
                null
            }
        }
    }

    companion object {
        @JvmStatic
        fun create() = CustomCallAdapterFactory()
    }

}