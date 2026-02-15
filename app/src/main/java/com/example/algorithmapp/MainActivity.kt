package com.example.algorithmapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var username: String? = null
    private var email: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = intent.getStringExtra("USERNAME")
        email = intent.getStringExtra("EMAIL")

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener(navListener)

        // Set default fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                AlgorithmsFragment()).commit()
            bottomNav.selectedItemId = R.id.navigation_algorithms
        }
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null

        when (item.itemId) {
            R.id.navigation_profile -> {
                val profileFragment = ProfileFragment()
                val bundle = Bundle()
                bundle.putString("USERNAME", username)
                bundle.putString("EMAIL", email)
                profileFragment.arguments = bundle
                selectedFragment = profileFragment
            }
            R.id.navigation_algorithms -> selectedFragment = AlgorithmsFragment()
            R.id.navigation_games -> selectedFragment = GamesFragment()
        }

        if (selectedFragment != null) {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,
                selectedFragment).commit()
        }

        true
    }

}