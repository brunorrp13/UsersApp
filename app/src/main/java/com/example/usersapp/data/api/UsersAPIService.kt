package com.example.usersapp.data.api

import com.example.usersapp.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface UsersAPIService {
    @GET
    suspend fun getUsers(@Url url: String): Response<List<User>>
}
