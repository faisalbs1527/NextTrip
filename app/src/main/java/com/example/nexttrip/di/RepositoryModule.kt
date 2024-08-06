package com.example.nexttrip.di

import com.example.nexttrip.data.DestinationRepoImpl
import com.example.nexttrip.domain.repository.DestinationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun provideDestinationRepo(destinationRepoImpl: DestinationRepoImpl): DestinationRepository
}