package com.example.digikala.di

import com.example.digikala.data.remote.AddressApi
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
object AddressApiModule {

    @Provides
    @Singleton
    fun provideAddressApiService(retrofit: Retrofit) : AddressApi =
        retrofit.create(AddressApi::class.java)
}