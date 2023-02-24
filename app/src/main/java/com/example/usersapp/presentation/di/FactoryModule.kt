package com.example.usersapp.presentation.di

import android.app.Application
import com.example.usersapp.domain.usecase.GetUsersUseCase
import com.example.usersapp.presentation.viewmodel.UsersViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideUsersViewModelFactory(
        application: Application,
        getUsersUseCase: GetUsersUseCase,
    ): UsersViewModelFactory {
        return UsersViewModelFactory(
            application, getUsersUseCase
        )
    }
}
