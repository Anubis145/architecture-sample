package com.example.core.network.callAdapter

import com.example.core.network.model.ApiResult
import kotlinx.serialization.json.Json
import retrofit2.Call
import retrofit2.CallAdapter
import java.lang.reflect.Type

internal class ApiResultCallAdapter(
    private val resultType: Type,
    private val json: Json,
) : CallAdapter<Type, Call<ApiResult<Type>>> {

    override fun responseType(): Type = resultType

    override fun adapt(proxy: Call<Type>): Call<ApiResult<Type>> {
        return ApiResultCall(proxy, json, resultType)
    }
}