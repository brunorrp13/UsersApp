package com.example.usersapp.domain.repository

import com.example.usersapp.data.model.APIResponse
import com.example.usersapp.data.util.Resource

interface UsersRepository {
    suspend fun getUsers(): Resource<APIResponse>
}