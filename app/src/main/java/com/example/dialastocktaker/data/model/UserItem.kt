package com.example.dialastocktaker.data.model

data class UserItem(
    val id: Long,
    val name: String,
    val email: String,
    val role: String,
    val contactNumber: String,
    val status: String,
    val loginCode: String
)
