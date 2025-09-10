package com.example.dialastocktaker.data.model

import java.util.Date

data class ReportItem(
    val id: String,
    val title: String,
    val jobId: String,
    val date: Date,
    val fileSize: String
)
