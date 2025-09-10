package com.example.dialastocktaker.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.dialastocktaker.data.entity.Message

@Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMessage(message: Message): Long

    @Update
    suspend fun updateMessage(message: Message)

    @Query("SELECT * FROM messages WHERE (senderId = :userId OR receiverId = :userId) ORDER BY timestamp DESC")
    suspend fun getMessagesByUserId(userId: Long): List<Message>

    @Query("SELECT * FROM messages WHERE (senderId = :userId1 AND receiverId = :userId2) OR (senderId = :userId2 AND receiverId = :userId1) ORDER BY timestamp ASC")
    suspend fun getConversation(userId1: Long, userId2: Long): List<Message>

    @Query("UPDATE messages SET isRead = 1 WHERE receiverId = :userId AND isRead = 0")
    suspend fun markAllAsRead(userId: Long)
}
