package com.example.dialastocktaker.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val contactNumber: String,
    val role: String, // admin, manager, coordinator, scanner, stocktaker, client
    val loginCode: String,
    val password: String,
    val isGroupLeader: Boolean = false
)
