package com.example.nexttrip.di

import com.example.nexttrip.data.DestinationRepoImpl
import com.example.nexttrip.data.FlightRepoImpl
import com.example.nexttrip.data.network.HotelRepoImpl
import com.example.nexttrip.domain.repository.DestinationRepository
import com.example.nexttrip.domain.repository.FlightRepository
import com.example.nexttrip.domain.repository.HotelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun provideDestinationRepo(destinationRepoImpl: DestinationRepoImpl): DestinationRepository

    @Binds
    abstract fun provideFlightRepo(flightRepoImpl: FlightRepoImpl): FlightRepository

    @Binds
    abstract fun provideHotelRepo(hotelRepoImpl: HotelRepoImpl): HotelRepository
}