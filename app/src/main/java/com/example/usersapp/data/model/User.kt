package com.example.usersapp.data.model

data class User(
    val avatar: String,
    val createdAt: String,
    val id: String,
    val knownIps: List<String>,
    val name: String,
    val profile: Profile,
    val username: String
)
