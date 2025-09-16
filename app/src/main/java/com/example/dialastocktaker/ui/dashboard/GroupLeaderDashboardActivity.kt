package com.example.dialastocktaker.ui.dashboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dialastocktaker.R
import com.example.dialastocktaker.databinding.ActivityGroupLeaderDashboardBinding
import com.example.dialastocktaker.ui.groupleader.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class GroupLeaderDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGroupLeaderDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGroupLeaderDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // Bottom navigation listener
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(GroupLeaderHomeFragment())
                R.id.nav_team -> loadFragment(GroupLeaderTeamFragment())
                R.id.nav_jobs -> loadFragment(GroupLeaderJobsFragment())
                R.id.nav_messages -> loadFragment(GroupLeaderMessagesFragment())
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
