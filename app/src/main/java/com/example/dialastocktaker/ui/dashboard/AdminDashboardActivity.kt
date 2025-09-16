package com.example.dialastocktaker.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dialastocktaker.R
import com.example.dialastocktaker.databinding.ActivityAdminDashboardBinding
import com.example.dialastocktaker.ui.admin.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class AdminDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup toolbar
        setSupportActionBar(binding.toolbar)

        // Setup bottom navigation
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(AdminHomeFragment())
                R.id.nav_users -> loadFragment(AdminUsersFragment())
                R.id.nav_jobs -> loadFragment(AdminJobsFragment())
                R.id.nav_messages -> loadFragment(AdminMessagesFragment())
                R.id.nav_reports -> loadFragment(AdminReportsFragment())
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
