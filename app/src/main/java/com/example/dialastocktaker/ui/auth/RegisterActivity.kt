package com.example.dialastocktaker.ui.auth

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dialastocktaker.R
import com.example.dialastocktaker.databinding.ActivityRegisterBinding
import com.example.dialastocktaker.viewmodel.AuthViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        setupSpinner()
        setupClickListeners()
        observeViewModel()
    }

    private fun setupSpinner() {
        val roles = arrayOf("Stocktaker", "Client")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roles)
        binding.spinnerRole.adapter = adapter
    }

    private fun setupClickListeners() {
        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString()
            val contactNumber = binding.etContactNumber.text.toString()
            val role = binding.spinnerRole.selectedItem.toString().lowercase()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            if (name.isEmpty() || contactNumber.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.register(name, contactNumber, role, password)
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }

    private fun observeViewModel() {
        viewModel.registrationResult.observe(this) { result ->
            if (result.success) {
                Toast.makeText(this, "Registration successful! Your login code is: ${result.loginCode}", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, result.errorMessage ?: "Registration failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
