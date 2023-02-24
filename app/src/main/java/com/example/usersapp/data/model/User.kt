package com.example.usersapp.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("knownIps")
    val knownIps: List<String>,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile")
    val profile: Profile,
    @SerializedName("username")
    val username: String
)
