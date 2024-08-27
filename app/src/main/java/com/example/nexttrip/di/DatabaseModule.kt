package com.example.nexttrip.di

import android.content.Context
import com.example.nexttrip.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDB(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.invoke(context)
    }
}