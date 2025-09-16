package com.example.dialastocktaker.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dialastocktaker.R
import com.example.dialastocktaker.databinding.ActivityCoordinatorDashboardBinding
import com.example.dialastocktaker.ui.coordinator.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class CoordinatorDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoordinatorDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoordinatorDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // Bottom navigation
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(CoordinatorHomeFragment())
                R.id.nav_team -> loadFragment(CoordinatorTeamFragment())
                R.id.nav_jobs -> loadFragment(CoordinatorJobsFragment())
                R.id.nav_messages -> loadFragment(CoordinatorMessagesFragment())
                else -> false
            }
        }

        // Default fragment
        if (savedInstanceState == null) {
            binding.bottomNav.selectedItemId = R.id.nav_home
        }
    }

    private fun loadFragment(fragment: Fragment): Boolean {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
        return true
    }
}
