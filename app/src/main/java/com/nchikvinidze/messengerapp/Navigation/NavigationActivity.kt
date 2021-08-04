package com.nchikvinidze.messengerapp.Navigation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nchikvinidze.messengerapp.Home.HomeFragment
import com.nchikvinidze.messengerapp.Profile.ProfileFragment
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.Search.SearchFragment

class NavigationActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.navigation)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val homeFragment = HomeFragment()
        val profileFragment = ProfileFragment()
        val searchFragment = SearchFragment()

        setCurrentFragment(homeFragment)

        navView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.Home -> setCurrentFragment(homeFragment)
                R.id.Search -> setCurrentFragment(searchFragment)
                R.id.Profile -> setCurrentFragment(profileFragment)
            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}