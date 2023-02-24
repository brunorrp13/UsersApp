package com.example.usersapp.data.repository.dataSourceImpl

import com.example.usersapp.data.api.UsersAPIService
import com.example.usersapp.data.model.APIResponse
import com.example.usersapp.data.repository.dataSource.UsersRemoteDataSource
import retrofit2.Response

class UsersRemoteDataSourceImpl(
        private val newsAPIService: UsersAPIService
): UsersRemoteDataSource {
    override suspend fun getUsers(): Response<APIResponse> {
          return newsAPIService.getUsers()
    }
}
