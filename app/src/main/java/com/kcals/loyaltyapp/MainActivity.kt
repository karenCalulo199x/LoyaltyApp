package com.kcals.loyaltyapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kcals.loyaltyapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        bottomNavigationView = binding.navView
        setContentView(binding.root)
        initNavigation()
        initViews()
    }

    private fun initNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_container) as NavHostFragment
        navController = navHostFragment.navController
        val navPageIdList = AppBarConfiguration(
            setOf(
                R.id.home_fragment_nav,
                R.id.account_fragment_nav,
                R.id.scan_fragment_nav,
                R.id.voucher_fragment_nav,
                R.id.more_fragment_nav
            )
        )
        setupActionBarWithNavController(navController, navPageIdList)
        bottomNavigationView.apply {
            setupWithNavController(navController)
            itemIconTintList = null
            setOnItemSelectedListener { item ->
                if (item.itemId != bottomNavigationView.selectedItemId) {
                    navController.popBackStack(item.itemId, inclusive = true, saveState = false)
                    navController.navigate(item.itemId)
                }
                true
            }
        }
    }

    private fun initViews() {

    }
}