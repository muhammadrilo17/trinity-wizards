package com.rilodev.core.di

import com.rilodev.core.data.source.local.LocalDataSource
import com.rilodev.core.data.source.local.room.AppDao
import com.rilodev.core.data.source.remote.RemoteDataSource
import com.rilodev.core.data.source.remote.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {
    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
    @Singleton
    @Provides
    fun provideRemoteDataSource(
        apiService: ApiService,
        dispatcher: CoroutineDispatcher
    ): RemoteDataSource = RemoteDataSource(apiService, dispatcher)

    @Singleton
    @Provides
    fun provideLocalDataSource(appDao: AppDao): LocalDataSource = LocalDataSource(appDao)
}