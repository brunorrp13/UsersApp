package com.example.usersapp.data.repository.dataSource

import com.example.usersapp.data.model.User
import retrofit2.Response

interface UsersRemoteDataSource {
    suspend fun getUsers(url: String):Response<List<User>>
}
