package com.example.core.network.callAdapter

import com.example.core.network.model.ApiResult
import com.example.core.network.model.handleApi
import kotlinx.serialization.json.Json
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

internal class ApiResultCall<T: Any>(
    private val proxy: Call<T>,
    private val json: Json,
    private val resultType: Type
) : Call<ApiResult<T>> {

    override fun enqueue(callback: Callback<ApiResult<T>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val networkResult = handleApi(resultType) { response }

                callback.onResponse(this@ApiResultCall, Response.success(networkResult))
            }
            override fun onFailure(call: Call<T>, t: Throwable) {
                val networkResult = ApiResult.ApiException<T>(t)

                callback.onResponse(this@ApiResultCall, Response.success(networkResult))
            }
        })
    }

    override fun execute(): Response<ApiResult<T>> = throw NotImplementedError()
    override fun clone(): Call<ApiResult<T>> = ApiResultCall(proxy.clone(), json, resultType)

    override fun isExecuted(): Boolean = proxy.isExecuted
    override fun isCanceled(): Boolean = proxy.isCanceled
    override fun request(): Request? = proxy.request()
    override fun timeout(): Timeout? = proxy.timeout()
    override fun cancel() {
        proxy.cancel()
    }
}