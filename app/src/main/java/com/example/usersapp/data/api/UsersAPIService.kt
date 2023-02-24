package com.example.usersapp.data.api

import com.example.usersapp.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET

interface UsersAPIService {
  @GET("api/v1/user")
  suspend fun getUsers(): Response<APIResponse>
}
