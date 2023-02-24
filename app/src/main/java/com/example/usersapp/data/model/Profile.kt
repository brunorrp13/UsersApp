package com.example.usersapp.data.model

import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("staticData")
    val staticData: List<Int>
)
