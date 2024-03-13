package com.arvind.exxceliqsolutiionstask.di

import com.arvind.exxceliqsolutiionstask.data.remote.ExxceliqAPI
import com.arvind.exxceliqsolutiionstask.data.repository.RemoteDataSourceImpl
import com.arvind.exxceliqsolutiionstask.domain.repository.RemoteDataSource
import com.arvind.exxceliqsolutiionstask.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun providesApi(okHttpClient: OkHttpClient): ExxceliqAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ExxceliqAPI::class.java)
    }

    @Provides
    @Singleton
    fun providesRemoteDataSource(
        exxceliqAPI: ExxceliqAPI,
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            exxceliqAPI = exxceliqAPI
        )
    }
}