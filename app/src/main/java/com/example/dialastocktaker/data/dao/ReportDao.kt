package com.example.dialastocktaker.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dialastocktaker.data.entity.Report

@Dao
interface ReportDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReport(report: Report): Long

    @Query("SELECT * FROM reports WHERE jobId = :jobId")
    suspend fun getReportsByJobId(jobId: Long): List<Report>

    @Query("SELECT * FROM reports WHERE createdBy = :userId")
    suspend fun getReportsByUserId(userId: Long): List<Report>
}
