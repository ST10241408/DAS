package com.example.dialastocktaker.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "jobs")
data class Job(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val jobId: String,
    val location: String,
    val date: Date,
    val clientId: Long,
    val status: String, // scheduled, in_progress, completed
    val assignedGroup: String? = null
)
