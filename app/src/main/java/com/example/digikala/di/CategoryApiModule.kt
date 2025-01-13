package com.example.digikala.di

import com.example.digikala.data.remote.CategoryApi
import com.example.digikala.data.remote.HomeApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CategoryApiModule {
    @Provides
    @Singleton
    fun provideCategoryApiService(retrofit: Retrofit) : CategoryApi =
        retrofit.create(CategoryApi::class.java)
}