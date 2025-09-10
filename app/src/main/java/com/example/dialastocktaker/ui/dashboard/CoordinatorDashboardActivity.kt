package com.example.dialastocktaker.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dialastocktaker.R
import com.example.dialastocktaker.databinding.ActivityCoordinatorDashboardBinding
import com.example.dialastocktaker.ui.coordinator.CoordinatorHomeFragment
import com.example.dialastocktaker.ui.coordinator.CoordinatorJobsFragment
import com.example.dialastocktaker.ui.coordinator.CoordinatorMessagesFragment
import com.example.dialastocktaker.ui.coordinator.CoordinatorTeamFragment
import com.example.dialastocktaker.ui.auth.LoginActivity

class CoordinatorDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoordinatorDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoordinatorDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()

        // Default fragment
        if (savedInstanceState == null) {
            loadFragment(CoordinatorHomeFragment())
            binding.navView.setCheckedItem(R.id.nav_home) // highlight
        }
    }

    private fun setupNavigation() {
        binding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(CoordinatorHomeFragment())
                R.id.nav_team -> loadFragment(CoordinatorTeamFragment())
                R.id.nav_jobs -> loadFragment(CoordinatorJobsFragment())
                R.id.nav_messages -> loadFragment(CoordinatorMessagesFragment())
                R.id.nav_logout -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
        return true
    }
}
