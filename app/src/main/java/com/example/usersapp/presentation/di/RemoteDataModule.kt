package com.example.usersapp.presentation.di

import com.example.usersapp.data.api.UsersAPIService
import com.example.usersapp.data.repository.dataSource.UsersRemoteDataSource
import com.example.usersapp.data.repository.dataSourceImpl.UsersRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideUsersRemoteDataSource(
        usersAPIService: UsersAPIService
    ): UsersRemoteDataSource {
        return UsersRemoteDataSourceImpl(usersAPIService)
    }
}
