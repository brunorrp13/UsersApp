package com.example.usersapp.domain.repository

import com.example.usersapp.data.util.Resource

interface UsersRepository {
    suspend fun getUsers(url: String): Resource
}
