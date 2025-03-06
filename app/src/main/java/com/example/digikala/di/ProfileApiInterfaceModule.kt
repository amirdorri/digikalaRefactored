package com.example.digikala.di

import com.example.digikala.data.remote.HomeApiInterface
import com.example.digikala.data.remote.ProfileApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ProfileApiInterfaceModule {

    @Provides
    @Singleton
    fun provideProfileApiService(retrofit: Retrofit) : ProfileApi =
        retrofit.create(ProfileApi::class.java)
}