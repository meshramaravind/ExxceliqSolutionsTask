package com.arvind.exxceliqsolutiionstask.di

import com.arvind.exxceliqsolutiionstask.data.repository.Repository
import com.arvind.exxceliqsolutiionstask.domain.usecases.GetUserInfoUseCase
import com.arvind.exxceliqsolutiionstask.domain.usecases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesUseCases(repository: Repository): UseCases {
        return UseCases(
            getUserInfoUseCase = GetUserInfoUseCase(repository),
        )
    }
}