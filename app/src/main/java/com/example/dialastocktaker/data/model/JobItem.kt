package com.example.dialastocktaker.data.model

import java.util.Date

data class JobItem(
    val id: String,
    val location: String,
    val date: Date,
    val team: String,
    val status: String
)
