package com.rilodev.core.data.source.remote

import com.google.gson.Gson
import com.rilodev.core.data.source.remote.network.ApiResponse
import com.rilodev.core.data.source.remote.network.ApiService
import com.rilodev.core.data.source.remote.response.DataResponse
import com.rilodev.core.data.source.remote.response.base.ErrorResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService, private val dispatcher: CoroutineDispatcher) {
    suspend fun getData(): Flow<ApiResponse<List<DataResponse>>> {
        return flow {
            try {
                val response = withContext(dispatcher) { apiService.getData() }
                emit(ApiResponse.Success(response.results))
            } catch (e: HttpException) {
                val result =
                    Gson().fromJson(e.response()?.errorBody()?.string(), ErrorResponse::class.java)

                emit(ApiResponse.Error(result?.message.toString()))
            }
        }
    }
}