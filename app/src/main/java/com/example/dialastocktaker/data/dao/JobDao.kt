package com.example.dialastocktaker.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dialastocktaker.data.entity.Job

@Dao
interface JobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: Job): Long

    @Update
    suspend fun updateJob(job: Job)

    @Query("SELECT * FROM jobs WHERE id = :id")
    suspend fun getJobById(id: Long): Job?

    @Query("SELECT * FROM jobs WHERE clientId = :clientId")
    suspend fun getJobsByClientId(clientId: Long): List<Job>

    @Query("SELECT * FROM jobs WHERE status = :status")
    suspend fun getJobsByStatus(status: String): List<Job>

    @Query("SELECT * FROM jobs WHERE assignedGroup = :groupId")
    suspend fun getJobsByAssignedGroup(groupId: String): List<Job>
}
