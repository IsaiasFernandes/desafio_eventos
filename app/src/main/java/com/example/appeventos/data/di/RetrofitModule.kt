package com.example.appeventos.data.di

import com.example.appeventos.data.service.EventService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideNewApiClient() : EventService {
        return  Retrofit.Builder()
            .baseUrl("https://5f5a8f24d44d640016169133.mockapi.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EventService::class.java)
    }
}