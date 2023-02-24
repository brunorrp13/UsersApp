package com.example.usersapp.domain.usecase

import com.example.usersapp.data.model.User
import com.example.usersapp.data.util.Resource
import com.example.usersapp.domain.repository.UsersRepository

class GetUsersUseCase(private val repository: UsersRepository) {

    suspend fun execute(url: String): Resource<List<User>> {
        return repository.getUsers(url)
    }
}
