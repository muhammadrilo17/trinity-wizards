package com.rilodev.core.di

import com.rilodev.core.domain.repositories.IAppRepositories
import com.rilodev.core.domain.usecase.AppUseCase
import com.rilodev.core.domain.usecase.GetPersonUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideAppUseCase(
        repositories: IAppRepositories
    ): AppUseCase {
        return AppUseCase(
            getPersonUseCase = GetPersonUseCase(repositories),
        )
    }
}