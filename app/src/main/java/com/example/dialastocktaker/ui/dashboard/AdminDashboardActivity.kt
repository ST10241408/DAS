package com.example.dialastocktaker.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dialastocktaker.R
import com.example.dialastocktaker.databinding.ActivityAdminDashboardBinding
import com.example.dialastocktaker.ui.admin.AdminHomeFragment
import com.example.dialastocktaker.ui.admin.AdminJobsFragment
import com.example.dialastocktaker.ui.admin.AdminMessagesFragment
import com.example.dialastocktaker.ui.admin.AdminReportsFragment
import com.example.dialastocktaker.ui.admin.AdminUsersFragment
import com.example.dialastocktaker.ui.auth.LoginActivity

class AdminDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminDashboardBinding
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup toolbar
        setSupportActionBar(binding.toolbar)

        // Setup drawer toggle (hamburger icon)
        toggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Handle navigation item clicks
        binding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(AdminHomeFragment())
                R.id.nav_users -> loadFragment(AdminUsersFragment())
                R.id.nav_jobs -> loadFragment(AdminJobsFragment())
                R.id.nav_messages -> loadFragment(AdminMessagesFragment())
                R.id.nav_reports -> loadFragment(AdminReportsFragment())
                R.id.nav_logout -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
            binding.drawerLayout.closeDrawers()
            true
        }

        // Default fragment
        if (savedInstanceState == null) {
            loadFragment(AdminHomeFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }
}
