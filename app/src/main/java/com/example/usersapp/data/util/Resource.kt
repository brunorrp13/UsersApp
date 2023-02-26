package com.example.usersapp.data.util

import com.example.usersapp.data.model.User

sealed class Resource {
    class Success(val data: List<User>) : Resource()
    class Error(val message: String) : Resource()
    object Loading : Resource()
}
