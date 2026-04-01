package com.example.core.network.callAdapter

import com.example.core.network.model.ApiResult
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ApiResultCallAdapterFactory private constructor(
    private val json: Json
) : CallAdapter.Factory() {
    override fun get(
        returnType: Type?,
        annotations: Array<out Annotation?>?,
        retrofit: Retrofit?
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) {
            return null
        }

        val callType = getParameterUpperBound(0, returnType as ParameterizedType)
        if (getRawType(callType) != ApiResult::class.java) {
            return null
        }

        val resultType = getParameterUpperBound(0, callType as ParameterizedType)
        return ApiResultCallAdapter(resultType, json)
    }

    companion object {
        fun create(json: Json) = ApiResultCallAdapterFactory(json)
    }
}