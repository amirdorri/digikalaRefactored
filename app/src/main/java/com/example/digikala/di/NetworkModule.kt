package com.example.digikala.di

import com.example.digikala.util.Constants.API_KEY
import com.example.digikala.util.Constants.BASE_URL
import com.example.digikala.util.Constants.TIMEOUT_SECOND
import com.example.digikala.util.Constants.USER_LANGUAGE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    internal fun interceptor() : HttpLoggingInterceptor{
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }



    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_SECOND, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SECOND, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_SECOND, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-api-key", API_KEY)
                .addHeader("lang", USER_LANGUAGE)
                chain.proceed(request.build())
        }
        .addInterceptor(interceptor())
        .build()

    @Provides
    @Singleton // check this out is it necessary?
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

}