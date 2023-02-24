package com.example.usersapp.domain.usecase

import com.example.usersapp.data.model.APIResponse
import com.example.usersapp.data.util.Resource
import com.example.usersapp.domain.repository.UsersRepository

class GetUsersUseCase(private val repository: UsersRepository) {

    suspend fun execute(): Resource<APIResponse> {
        return repository.getUsers()
    }
}
