package com.example.digikala.di

import android.content.Context
import androidx.room.Room
import com.example.digikala.data.database.CartDao
import com.example.digikala.data.database.DigikalaDatabase
import com.example.digikala.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context = context,
        klass = DigikalaDatabase::class.java,
        name = DATABASE_NAME
    ).build()
}

@Module
@InstallIn(SingletonComponent::class)
object CartDaoModule {
    @Provides
    @Singleton
    fun provideCartDao(
        database: DigikalaDatabase
    ): CartDao = database.cartDao()


}