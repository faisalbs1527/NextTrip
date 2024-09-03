package com.example.nexttrip.di

import com.example.nexttrip.data.network.ApiClient
import com.example.nexttrip.data.network.api.DestinationAPI
import com.example.nexttrip.data.network.api.FlightAPI
import com.example.nexttrip.data.network.api.HotelAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return ApiClient.getRetrofit()
    }

    @Provides
    @Singleton
    fun provideDestinationApi(retrofit: Retrofit): DestinationAPI {
        return retrofit.create(DestinationAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideFlightApi(retrofit: Retrofit): FlightAPI {
        return retrofit.create(FlightAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideHotelApi(retrofit: Retrofit): HotelAPI {
        return retrofit.create(HotelAPI::class.java)
    }
}