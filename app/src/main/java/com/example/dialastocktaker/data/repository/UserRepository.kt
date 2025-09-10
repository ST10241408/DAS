package com.example.dialastocktaker.data.repository

import com.example.dialastocktaker.data.dao.UserDao
import com.example.dialastocktaker.data.entity.User

class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user: User): Long {
        return userDao.insertUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun getUserById(id: Long): User? {
        return userDao.getUserById(id)
    }

    suspend fun getUserByLoginCode(loginCode: String): User? {
        return userDao.getUserByLoginCode(loginCode)
    }

    suspend fun getUserCount(): Int {
        return userDao.getUserCount()
    }

    suspend fun getUserCountByRole(role: String): Int {
        return userDao.getUserCountByRole(role)
    }

    suspend fun getUsersByRole(role: String): List<User> {
        return userDao.getUsersByRole(role)
    }
}
