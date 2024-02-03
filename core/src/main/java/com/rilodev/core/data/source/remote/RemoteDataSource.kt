package com.rilodev.core.data.source.remote

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rilodev.core.data.source.remote.network.ApiResponse
import com.rilodev.core.data.source.remote.network.ApiService
import com.rilodev.core.data.source.remote.response.PersonResponse
import com.rilodev.core.data.source.remote.response.base.ErrorResponse
import com.rilodev.core.helpers.utils.Utils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService,
    private val dispatcher: CoroutineDispatcher,
    private val context: Context
) {
    suspend fun getData(): Flow<ApiResponse<List<PersonResponse>>> {
        return flow {
            try {
                val response = withContext(dispatcher) {
                    Utils.loadJsonFromAssets(context, "data")
                }

                val personListType = object : TypeToken<List<PersonResponse>>() {}.type
                val personList = Gson().fromJson<List<PersonResponse>>(response, personListType)

                emit(ApiResponse.Success(personList))
            } catch (e: HttpException) {
                val result =
                    Gson().fromJson(e.response()?.errorBody()?.string(), ErrorResponse::class.java)

                emit(ApiResponse.Error(result?.message.toString()))
            }
        }
    }
}