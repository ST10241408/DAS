package com.example.dialastocktaker.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "reports")
data class Report(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val jobId: Long,
    val title: String,
    val filePath: String,
    val createdAt: Date,
    val createdBy: Long
)
