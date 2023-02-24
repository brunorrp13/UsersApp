package com.example.usersapp.presentation.di

import com.example.usersapp.data.repository.UsersRepositoryImpl
import com.example.usersapp.data.repository.dataSource.UsersRemoteDataSource
import com.example.usersapp.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideNewsRepository(
        usersRemoteDataSource: UsersRemoteDataSource
    ): UsersRepository {
        return UsersRepositoryImpl(
            usersRemoteDataSource
        )
    }
}
