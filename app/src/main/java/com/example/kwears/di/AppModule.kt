package com.example.kwears.di

import com.example.kwears.data.repository.ProductRepository
import com.example.kwears.data.repository.ProductRepositoryImpl
import com.example.kwears.network.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideProductApi():Api = Api.getClient()

    @Provides
    @Singleton
    fun provideProductRepository(api:Api):ProductRepository = ProductRepositoryImpl(api)
}