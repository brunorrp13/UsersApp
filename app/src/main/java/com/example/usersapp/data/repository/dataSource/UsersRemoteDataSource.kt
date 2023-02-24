package com.example.usersapp.data.repository.dataSource

import com.example.usersapp.data.model.APIResponse
import retrofit2.Response

interface UsersRemoteDataSource {
    suspend fun getUsers():Response<APIResponse>
}
