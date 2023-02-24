package com.example.usersapp.data.repository.dataSourceImpl

import com.example.usersapp.data.api.UsersAPIService
import com.example.usersapp.data.model.User
import com.example.usersapp.data.repository.dataSource.UsersRemoteDataSource
import retrofit2.Response

class UsersRemoteDataSourceImpl(
        private val usersAPIService: UsersAPIService
): UsersRemoteDataSource {
    override suspend fun getUsers(url: String): Response<List<User>> {
          return usersAPIService.getUsers(url)
    }
}
