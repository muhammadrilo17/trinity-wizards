package com.rilodev.core.di

import com.rilodev.core.data.repositories.AppRepositories
import com.rilodev.core.domain.repositories.IAppRepositories
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {
    @Binds
    abstract fun provideAppRepositories(repositories: AppRepositories): IAppRepositories
}