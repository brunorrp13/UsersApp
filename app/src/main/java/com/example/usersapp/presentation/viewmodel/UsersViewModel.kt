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
import java.lang.Exception

class UsersViewModel(
    private val app: Application,
    private val getUsersUseCase: GetUsersUseCase
) : AndroidViewModel(app) {
    var uiState = MutableStateFlow<Resource<List<User>>>(Resource.Loading())
        private set

    fun getUsers(url: String) = viewModelScope.launch(Dispatchers.IO) {
        uiState.emit(Resource.Loading())
        try {
            if (isNetworkAvailable(app)) {
                val apiResult = getUsersUseCase.execute(url.trim()+app.getString(R.string.endpoint))
                uiState.emit(apiResult)
            } else {
                uiState.emit(Resource.Error(app.getString(R.string.internet_is_not_available)))
            }

        } catch (e: Exception) {
            uiState.emit(Resource.Error(e.message.toString()))
        }
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork =
                connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ->true
                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }
}
