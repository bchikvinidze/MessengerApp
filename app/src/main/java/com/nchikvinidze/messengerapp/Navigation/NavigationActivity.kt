package com.nchikvinidze.messengerapp.Navigation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nchikvinidze.messengerapp.DependencyInjectorImpl
import com.nchikvinidze.messengerapp.Home.HomeFragment
import com.nchikvinidze.messengerapp.Profile.ProfileFragment
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.Search.SearchActivity

class NavigationActivity: AppCompatActivity(), NavigationView.View {
    private lateinit var presenter: NavigationView.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.navigation)
        setPresenter(NavigationPresenter(this, DependencyInjectorImpl()))
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        presenter.onViewCreated()

        navView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.Home -> presenter.homeClicked()
                R.id.Profile -> presenter.profileClicked()
            }
            true
        }

        fab.setOnClickListener {
            presenter.fabClicked()
        }
    }

    override fun showSearch() {
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
    }

    override fun showProfile() {
        val profileFragment = ProfileFragment.newInstance(intent.getStringExtra("nick")!!)
        setCurrentFragment(profileFragment)
    }

    override fun showHome() {
        val homeFragment = HomeFragment()
        setCurrentFragment(homeFragment)
    }

    override fun setPresenter(presenter: NavigationView.Presenter) {
        this.presenter = presenter
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }
}