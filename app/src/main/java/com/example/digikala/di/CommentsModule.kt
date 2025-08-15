package com.example.digikala.di

import com.example.digikala.data.remote.CommentsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CommentsModule {

    @Provides
    @Singleton
    fun provideCommentsApiService(retrofit: Retrofit) : CommentsApi =
        retrofit.create(CommentsApi::class.java)
}