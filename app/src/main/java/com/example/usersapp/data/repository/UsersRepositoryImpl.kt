package com.example.usersapp.data.repository

import com.example.usersapp.data.model.APIResponse
import com.example.usersapp.data.repository.dataSource.UsersRemoteDataSource
import com.example.usersapp.data.util.Resource
import com.example.usersapp.domain.repository.UsersRepository
import retrofit2.Response

class UsersRepositoryImpl(
    private val usersRemoteDataSource: UsersRemoteDataSource,
): UsersRepository {
    override suspend fun getUsers(): Resource<APIResponse> {
        return responseToResource(usersRemoteDataSource.getUsers())
    }
    private fun responseToResource(response:Response<APIResponse>):Resource<APIResponse>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}
