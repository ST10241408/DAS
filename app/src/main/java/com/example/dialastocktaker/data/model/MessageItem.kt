package com.example.dialastocktaker.data.model
import java.util.Date


data class MessageItem(
    val id: Long,
    val senderId: Int,
    val senderName: String,
    val receiverId: Int,
    val receiverName: String,
    val content: String,
    val timestamp: Date,
    val isFromCurrentUser: Boolean,
    val subject: String = "",
    val isRead: Boolean = false,
    val date: Date = Date()
)
