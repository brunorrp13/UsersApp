package com.example.usersapp.presentation.di

import com.example.usersapp.domain.repository.UsersRepository
import com.example.usersapp.domain.usecase.GetUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetUsersUseCase(
        usersRepository: UsersRepository
    ): GetUsersUseCase {
        return GetUsersUseCase(usersRepository)
    }
}
