package com.example.dialastocktaker.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dialastocktaker.data.AppDatabase
import com.example.dialastocktaker.data.entity.User
import com.example.dialastocktaker.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private val _registrationResult = MutableLiveData<RegistrationResult>()
    val registrationResult: LiveData<RegistrationResult> = _registrationResult

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)

        // Add demo users if database is empty
        viewModelScope.launch {
            if (repository.getUserCount() == 0) {
                addDemoUsers()
            }
        }
    }

    fun login(loginCode: String, password: String) {
        viewModelScope.launch {
            val user = repository.getUserByLoginCode(loginCode)

            if (user != null && user.password == password) {
                _loginResult.postValue(LoginResult(true, user.role, null))
            } else {
                _loginResult.postValue(LoginResult(false, "", "Invalid login credentials"))
            }
        }
    }

    fun register(name: String, contactNumber: String, role: String, password: String) {
        viewModelScope.launch {
            try {
                // Generate a unique login code based on role
                val prefix = when (role) {
                    "stocktaker" -> "STK-"
                    "client" -> "CLT-"
                    else -> "USR-"
                }

                val userCount = repository.getUserCountByRole(role)
                val suffix = String.format("%08d", userCount + 1)
                val loginCode = prefix + suffix

                val user = User(
                    id = 0,
                    name = name,
                    contactNumber = contactNumber,
                    role = role,
                    loginCode = loginCode,
                    password = password,
                    isGroupLeader = false
                )

                repository.insertUser(user)
                _registrationResult.postValue(RegistrationResult(true, loginCode, null))
            } catch (e: Exception) {
                _registrationResult.postValue(RegistrationResult(false, null, e.message))
            }
        }
    }

    private suspend fun addDemoUsers() {
        withContext(Dispatchers.IO) {
            val demoUsers = listOf(
                User(0, "Admin User", "1234567890", "admin", "ADM-00000001", "Admin@1234", false),
                User(0, "Manager User", "2345678901", "manager", "MGR-00000022", "Manager@1234", false),
                User(0, "Coordinator User", "3456789012", "coordinator", "CRD-0011223344", "Coord@1234", false),
                User(0, "Scanner User", "4567890123", "scanner", "SCN-0987654321", "Scanner@1234", false),
                User(0, "Stocktaker User", "5678901234", "stocktaker", "STK-1234567890", "Stock@1234", false),
                User(0, "Group Leader", "6789012345", "stocktaker", "STK-3456789012", "Leader@1234", true),
                User(0, "Client User", "7890123456", "client", "CLT-00000001", "Client@1234", false)
            )

            demoUsers.forEach { user ->
                repository.insertUser(user)
            }
        }
    }

    data class LoginResult(
        val success: Boolean,
        val userRole: String,
        val errorMessage: String?
    )

    data class RegistrationResult(
        val success: Boolean,
        val loginCode: String?,
        val errorMessage: String?
    )
}
