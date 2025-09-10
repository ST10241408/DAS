package com.example.dialastocktaker.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.dialastocktaker.R
import com.example.dialastocktaker.databinding.ActivityStocktakerDashboardBinding
import com.example.dialastocktaker.ui.stocktaker.StocktakerHomeFragment
import com.example.dialastocktaker.ui.stocktaker.StocktakerJobsFragment
import com.example.dialastocktaker.ui.stocktaker.StocktakerMessagesFragment
import com.example.dialastocktaker.ui.stocktaker.StocktakerProfileFragment
import com.example.dialastocktaker.ui.auth.LoginActivity

class StocktakerDashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStocktakerDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStocktakerDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()

        // Default fragment
        if (savedInstanceState == null) {
            loadFragment(StocktakerHomeFragment())
            binding.bottomNavView.selectedItemId = R.id.nav_home
        }
    }

    private fun setupNavigation() {
        //  uses bottomNavView, not navView
        binding.bottomNavView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> loadFragment(StocktakerHomeFragment())
                R.id.nav_jobs -> loadFragment(StocktakerJobsFragment())
                R.id.nav_messages -> loadFragment(StocktakerMessagesFragment())
                R.id.nav_profile -> loadFragment(StocktakerProfileFragment())
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
            .replace(R.id.nav_host_fragment, fragment) // ✅ matches your XML id
            .commit()
        return true
    }
}
