package com.example.data.common.networkHelper

import com.example.core.network.model.ApiResult
import com.example.domain.common.ArchResult
import com.example.domain.common.DataError

interface NetworkHelper {
    suspend fun <I : Any, O> fetchResult(
        mapper: (I) -> O,
        apiCall: suspend () -> ApiResult<I>
    ): ArchResult<O, DataError.NetworkError>

    suspend fun <I : Any> fetchResult(
        apiCall: suspend () -> ApiResult<I>
    ): ArchResult<Unit, DataError.NetworkError>
}
