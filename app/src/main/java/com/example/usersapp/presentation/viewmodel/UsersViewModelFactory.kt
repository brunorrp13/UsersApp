package com.example.usersapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.usersapp.domain.usecase.GetUsersUseCase

class UsersViewModelFactory(
    private val app:Application,
    private val usersUseCase: GetUsersUseCase,
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UsersViewModel(
            app,
            usersUseCase
        ) as T
    }
}
