package com.example.weatherforecastapp.hilt

import com.example.weatherforecastapp.api.FakeApiService
import com.example.weatherforecastapp.data.network.ApiService
import com.example.weatherforecastapp.di.ApiServiceModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [ApiServiceModule::class])
abstract class FakeApiServiceModule {

    @Binds
    @Singleton
    abstract fun providesApiService(fakeApiService: FakeApiService): ApiService
}