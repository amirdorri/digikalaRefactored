package com.example.digikala.di

import com.example.digikala.data.remote.CheckoutApi
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
object CheckoutApiModule {

    @Provides
    @Singleton
    fun provideCheckoutApiService(retrofit: Retrofit) : CheckoutApi =
        retrofit.create(CheckoutApi::class.java)
}