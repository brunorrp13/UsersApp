package com.example.usersapp.data.repository

import com.example.usersapp.data.model.User
import com.example.usersapp.data.repository.dataSource.UsersRemoteDataSource
import com.example.usersapp.data.util.Resource
import com.example.usersapp.domain.repository.UsersRepository
import retrofit2.Response

class UsersRepositoryImpl(
    private val usersRemoteDataSource: UsersRemoteDataSource,
) : UsersRepository {
    override suspend fun getUsers(url: String): Resource<List<User>> {
        return responseToResource(usersRemoteDataSource.getUsers(url))
    }

    private fun responseToResource(response: Response<List<User>>): Resource<List<User>> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}
