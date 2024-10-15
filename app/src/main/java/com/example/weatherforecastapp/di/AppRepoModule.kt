package com.example.weatherforecastapp.di

import com.example.weatherforecastapp.data.network.ApiRepository
import com.example.weatherforecastapp.data.network.ApiRepositoryImpl
import com.example.weatherforecastapp.data.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
class AppRepoModule {
    @Provides
    fun providesApiRepository(apiServices: ApiService): ApiRepository =
        ApiRepositoryImpl(apiServices)
}

@InstallIn(SingletonComponent::class)
@Module
class ApiServiceModule {
    @Provides
    fun providesApiServices(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}