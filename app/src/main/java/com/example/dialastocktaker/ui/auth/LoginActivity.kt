package com.example.dialastocktaker.ui.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.dialastocktaker.databinding.ActivityLoginBinding
import com.example.dialastocktaker.ui.dashboard.AdminDashboardActivity
import com.example.dialastocktaker.ui.dashboard.ClientDashboardActivity
import com.example.dialastocktaker.ui.dashboard.GroupLeaderDashboardActivity
import com.example.dialastocktaker.ui.dashboard.ManagerDashboardActivity
import com.example.dialastocktaker.ui.dashboard.StocktakerDashboardActivity
import com.example.dialastocktaker.ui.dashboard.CoordinatorDashboardActivity
import com.example.dialastocktaker.ui.dashboard.ScannerDashboardActivity
import com.example.dialastocktaker.viewmodel.AuthViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[AuthViewModel::class.java]

        setupClickListeners()
        observeViewModel()
    }

    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            val loginCode = binding.etLoginCode.text.toString()
            val password = binding.etPassword.text.toString()

            if (loginCode.isNotEmpty() && password.isNotEmpty()) {
                viewModel.login(loginCode, password)
            } else {
                Toast.makeText(this, "Please enter login code and password", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        // Demo login buttons
        binding.btnDemoAdmin.setOnClickListener {
            binding.etLoginCode.setText("ADM-00000001")
            binding.etPassword.setText("Admin@1234")
        }

        binding.btnDemoManager.setOnClickListener {
            binding.etLoginCode.setText("MGR-00000022")
            binding.etPassword.setText("Manager@1234")
        }

        binding.btnDemoCoordinator.setOnClickListener {
            binding.etLoginCode.setText("CRD-0011223344")
            binding.etPassword.setText("Coord@1234")
        }

        binding.btnDemoStocktaker.setOnClickListener {
            binding.etLoginCode.setText("STK-1234567890")
            binding.etPassword.setText("Stock@1234")
        }

        binding.btnDemoScanner.setOnClickListener {
            binding.etLoginCode.setText("SCN-0987654321")
            binding.etPassword.setText("Scanner@1234")
        }

        binding.btnDemoGroupLeader.setOnClickListener {
            binding.etLoginCode.setText("STK-3456789012")
            binding.etPassword.setText("Leader@1234")
        }

        binding.btnDemoClient.setOnClickListener {
            binding.etLoginCode.setText("CLT-00000001")
            binding.etPassword.setText("Client@1234")
        }
    }

    private fun observeViewModel() {
        viewModel.loginResult.observe(this) { result ->
            if (result.success) {
                navigateToRoleDashboard(result.userRole)
            } else {
                Toast.makeText(this, result.errorMessage ?: "Login failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToRoleDashboard(role: String) {
        val intent = when (role) {
            "admin" -> Intent(this, AdminDashboardActivity::class.java)
            "manager" -> Intent(this, ManagerDashboardActivity::class.java)
            "coordinator" -> Intent(this, CoordinatorDashboardActivity::class.java)
            "scanner" -> Intent(this, ScannerDashboardActivity::class.java)
            "stocktaker" -> Intent(this, StocktakerDashboardActivity::class.java)
            "groupleader" -> Intent(this, GroupLeaderDashboardActivity::class.java)
            "client" -> Intent(this, ClientDashboardActivity::class.java)
            else -> {
                Toast.makeText(this, "Unknown role: $role", Toast.LENGTH_SHORT).show()
                return
            }
        }
        startActivity(intent)
        finish()
    }
}
