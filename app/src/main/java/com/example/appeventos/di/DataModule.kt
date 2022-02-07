package com.example.appeventos.di

import com.example.appeventos.data.repository.Repository
import com.example.appeventos.data.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindRepository(
        repositoryImpl: RepositoryImpl
    ) : Repository
}