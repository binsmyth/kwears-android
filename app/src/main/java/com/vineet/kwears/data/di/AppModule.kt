package com.vineet.kwears.data.di

import android.content.Context
import androidx.room.Room
import com.vineet.kwears.data.database.AppDatabase
import com.vineet.kwears.data.repository.ProductRepository
import com.vineet.kwears.data.repository.ProductRepositoryImpl
import com.vineet.kwears.data.network.Api
import com.vineet.kwears.data.database.typeconverters.ListConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideProductApi(): Api = Api.getClient()

    @Provides
    @Singleton
    fun provideProductRepository(api: Api): ProductRepository = ProductRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                .addTypeConverter(ListConverter::class).build()
    }
}