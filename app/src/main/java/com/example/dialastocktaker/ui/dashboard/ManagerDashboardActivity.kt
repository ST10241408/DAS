package com.example.dialastocktaker.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dialastocktaker.R
import com.example.dialastocktaker.databinding.ActivityManagerDashboardBinding
import com.example.dialastocktaker.ui.manager.ManagerHomeFragment
import com.example.dialastocktaker.ui.manager.ManagerJobsFragment
import com.example.dialastocktaker.ui.manager.ManagerMessagesFragment
import com.example.dialastocktaker.ui.manager.ManagerReportsFragment
import com.example.dialastocktaker.ui.manager.ManagerTeamFragment
import com.example.dialastocktaker.ui.auth.LoginActivity

class ManagerDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityManagerDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManagerDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()

        // Default fragment
        if (savedInstanceState == null) {
            loadFragment(ManagerHomeFragment())
            binding.navView.setCheckedItem(R.id.nav_home) // drawer version
        }
    }

    private fun setupNavigation() {
        binding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(ManagerHomeFragment())
                R.id.nav_team -> loadFragment(ManagerTeamFragment())
                R.id.nav_jobs -> loadFragment(ManagerJobsFragment())
                R.id.nav_messages -> loadFragment(ManagerMessagesFragment())
                R.id.nav_reports -> loadFragment(ManagerReportsFragment())
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
