package com.nchikvinidze.messengerapp.Navigation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.nchikvinidze.messengerapp.Chat.ChatActivity
import com.nchikvinidze.messengerapp.DependencyInjectorImpl
import com.nchikvinidze.messengerapp.Home.HomeFragment
import com.nchikvinidze.messengerapp.Home.MessageClickListener
import com.nchikvinidze.messengerapp.Home.ScrollListener
import com.nchikvinidze.messengerapp.Login.LoginActivity
import com.nchikvinidze.messengerapp.Profile.ProfileFragment
import com.nchikvinidze.messengerapp.Profile.SignOutClickListener
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.Search.SearchActivity
import com.nchikvinidze.messengerapp.data.User

class NavigationActivity: AppCompatActivity(), NavigationView.View, ScrollListener, MessageClickListener, SignOutClickListener {
    private lateinit var presenter: NavigationView.Presenter
    private lateinit var navigationView: BottomNavigationView
    private lateinit var navigationBar: BottomAppBar
    private val homeFragment = HomeFragment()
    private val profileFragment = ProfileFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.navigation)
        setPresenter(NavigationPresenter(this, DependencyInjectorImpl()))
        navigationView = findViewById(R.id.nav_view)
        navigationBar = findViewById(R.id.bottomAppBar)
        val fab: FloatingActionButton = findViewById(R.id.fab)
        homeFragment.scrollListener = this
        homeFragment.clickListener = this
        profileFragment.clickListener = this

        presenter.onViewCreated()

        navigationView.setOnNavigationItemSelectedListener { menuItem ->
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
        val nick = intent.getStringExtra("nick")!!
        val intent = Intent(this, SearchActivity::class.java)
        intent.putExtra("nick", nick)
        startActivity(intent)
    }

    override fun showProfile() {
        setCurrentFragment(profileFragment)
    }

    override fun showHome() {
        setCurrentFragment(homeFragment)
    }

    override fun setPresenter(presenter: NavigationView.Presenter) {
        this.presenter = presenter
    }

    override fun showBottomNav() {
        navigationBar.isVisible = true
    }

    override fun hideBottomNav() {
        navigationBar.isVisible = false
    }

    override fun showChat(user: String) {
        val nick = intent.getStringExtra("nick")!!
        var intent = Intent(this, ChatActivity::class.java)
        intent.putExtra("nick", nick)
        intent.putExtra("recipient", user)
        startActivity(intent)
    }

    override fun onClicked(user: String) {
        showChat(user)
    }

    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

    override fun signOutClicked() {
        var intent = Intent(this, LoginActivity::class.java)
        intent.putExtra("status", "logout")
        finish()
        startActivity(intent)
    }
}