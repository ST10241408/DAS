package com.example.dialastocktaker.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dialastocktaker.R
import com.example.dialastocktaker.databinding.ActivityGroupLeaderDashboardBinding
import com.example.dialastocktaker.ui.groupleader.GroupLeaderHomeFragment
import com.example.dialastocktaker.ui.groupleader.GroupLeaderJobsFragment
import com.example.dialastocktaker.ui.groupleader.GroupLeaderMessagesFragment
import com.example.dialastocktaker.ui.groupleader.GroupLeaderTeamFragment
import com.example.dialastocktaker.ui.auth.LoginActivity

class GroupLeaderDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGroupLeaderDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGroupLeaderDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()

        // Default fragment
        if (savedInstanceState == null) {
            loadFragment(GroupLeaderHomeFragment())
            binding.navView.setCheckedItem(R.id.nav_home) // ✅ drawer version
        }
    }

    private fun setupNavigation() {
        binding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(GroupLeaderHomeFragment())
                R.id.nav_team -> loadFragment(GroupLeaderTeamFragment())
                R.id.nav_jobs -> loadFragment(GroupLeaderJobsFragment())
                R.id.nav_messages -> loadFragment(GroupLeaderMessagesFragment())
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
