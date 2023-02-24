package com.example.usersapp.presentation.di

import com.example.usersapp.presentation.adapter.UsersAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
    @Singleton
    @Provides
    fun provideUsersAdapter(): UsersAdapter {
        return UsersAdapter()
    }
}
