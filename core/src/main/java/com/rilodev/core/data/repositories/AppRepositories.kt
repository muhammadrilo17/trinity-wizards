package com.rilodev.core.data.repositories

import com.rilodev.core.data.source.local.LocalDataSource
import com.rilodev.core.data.source.remote.RemoteDataSource
import com.rilodev.core.data.source.remote.network.ApiResponse
import com.rilodev.core.domain.model.DataModel
import com.rilodev.core.domain.repositories.IAppRepositories
import com.rilodev.core.helpers.mapper.DataMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class AppRepositories @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val dispatcher: CoroutineDispatcher,
): IAppRepositories {
    override fun getData(): Flow<Resource<List<DataModel>>> {
        return flow {
            emit(Resource.Loading())

            try {
                when (val apiResponse = withContext(dispatcher){ remoteDataSource.getData().first() }) {
                    is ApiResponse.Success -> {
                        val response = apiResponse.data
                        val data =
                            response.map { DataMapper.responseToModel(it) }.toList()
                        emit(Resource.Success(data))
                    }

                    is ApiResponse.Error -> {
                        emit(Resource.Error(apiResponse.errorMessage))
                    }

                    is ApiResponse.Empty -> {
                        emit(Resource.Empty())
                    }
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "An error occurred"))
            }
        }
    }
}