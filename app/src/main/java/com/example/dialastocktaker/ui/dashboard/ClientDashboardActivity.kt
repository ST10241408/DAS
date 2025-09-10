package com.example.dialastocktaker.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dialastocktaker.R
import com.example.dialastocktaker.databinding.ActivityClientDashboardBinding
import com.example.dialastocktaker.ui.auth.LoginActivity
import com.example.dialastocktaker.ui.client.ClientHomeFragment
import com.example.dialastocktaker.ui.client.ClientJobsFragment
import com.example.dialastocktaker.ui.client.ClientMessagesFragment
import com.example.dialastocktaker.ui.client.ClientProfileFragment
import com.example.dialastocktaker.ui.client.ClientReportsFragment

class ClientDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityClientDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClientDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()

        // Default fragment
        if (savedInstanceState == null) {
            loadFragment(ClientHomeFragment())
            binding.bottomNav.selectedItemId = R.id.nav_home
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(ClientHomeFragment())
                R.id.nav_jobs -> loadFragment(ClientJobsFragment())
                R.id.nav_reports -> loadFragment(ClientReportsFragment())
                R.id.nav_messages -> loadFragment(ClientMessagesFragment())
                R.id.nav_profile -> loadFragment(ClientProfileFragment())
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
