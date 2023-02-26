package com.example.usersapp.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.usersapp.R
import com.example.usersapp.data.model.User
import com.example.usersapp.data.util.Resource
import com.example.usersapp.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception

class UsersViewModel(
    private val app: Application,
    private val getUsersUseCase: GetUsersUseCase
) : AndroidViewModel(app) {
    var uiState = MutableStateFlow<Resource>(Resource.Loading)
        private set

    fun getUsers(url: String) =
        runBlocking {
            launch {
                uiState.emit(Resource.Loading)
                try {
                    val apiResult =
                        getUsersUseCase.execute(url.trim() + app.getString(R.string.endpoint))
                    uiState.emit(apiResult)
                } catch (e: Exception) {
                    uiState.emit(Resource.Error(e.message.toString()))
                }
            }
        }
}
