package com.nchikvinidze.messengerapp.Search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nchikvinidze.messengerapp.Chat.ChatActivity
import com.nchikvinidze.messengerapp.DependencyInjectorImpl
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.data.User

class SearchActivity: AppCompatActivity(), SearchList.View, ClickListener {
    private lateinit var presenter: SearchList.Presenter
    private lateinit var adapter: SearchAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search)
        adapter = SearchAdapter(ArrayList())
        adapter.clickListener = this
        val recyclerView: RecyclerView = findViewById(R.id.search_recycler_view)
        recyclerView.adapter = adapter
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        setPresenter(SearchPresenter(this, DependencyInjectorImpl()))
        val toolbar: Toolbar = findViewById(R.id.search_toolbar)
        toolbar.setNavigationOnClickListener {
            presenter.backClicked()
        }
        val searchView: SearchView = findViewById(R.id.search_user)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.length ?: 0 >= 3) {
                    presenter.search(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }
        })
        presenter.onViewCreated()
    }

    override fun setPresenter(presenter: SearchList.Presenter) {
        this.presenter = presenter
    }

    override fun showHome() {
        finish()
    }

    override fun showUsers(data: List<User>) {
        adapter.update(data)
    }

    override fun onClicked(user: User) {
        presenter.userClicked(user)
    }

    override fun showChat(user: User) {
        val nick = intent.getStringExtra("nick")!!
        var intent = Intent(this, ChatActivity::class.java)
        intent.putExtra("nick", nick)
        intent.putExtra("recipient", user.nick)
        startActivity(intent)
    }
}