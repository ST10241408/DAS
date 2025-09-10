package com.example.dialastocktaker.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dialastocktaker.R
import com.example.dialastocktaker.databinding.ActivityScannerDashboardBinding
import com.example.dialastocktaker.ui.scanner.ScannerHomeFragment
import com.example.dialastocktaker.ui.scanner.ScannerJobsFragment
import com.example.dialastocktaker.ui.scanner.ScannerMessagesFragment
import com.example.dialastocktaker.ui.scanner.ScannerUploadsFragment
import com.example.dialastocktaker.ui.auth.LoginActivity

class ScannerDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScannerDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScannerDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()

        // Default fragment
        if (savedInstanceState == null) {
            loadFragment(ScannerHomeFragment())
            binding.bottomNavView.selectedItemId = R.id.nav_home
        }
    }

    private fun setupNavigation() {
        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(ScannerHomeFragment())
                R.id.nav_jobs -> loadFragment(ScannerJobsFragment())
                R.id.nav_uploads -> loadFragment(ScannerUploadsFragment())
                R.id.nav_messages -> loadFragment(ScannerMessagesFragment())
                else -> false
            }
        }

        binding.btnLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
        return true
    }
}
