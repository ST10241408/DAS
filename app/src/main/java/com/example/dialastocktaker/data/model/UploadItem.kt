package com.example.dialastocktaker.data.model

import java.util.Date

data class UploadItem(
    val id: String,
    val jobId: String,
    val location: String,
    val timestamp: Long,
    val itemCount: Int,
    val status: String,

    // User info
    val name: String,
    val role: String,
    val contactNumber: String,
    val loginCode: String
)

