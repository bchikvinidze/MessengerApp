package com.nchikvinidze.messengerapp.Search

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nchikvinidze.messengerapp.Chat.ChatActivity
import com.nchikvinidze.messengerapp.DependencyInjectorImpl
import com.nchikvinidze.messengerapp.LoadingDialog.LoadingDialog
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.data.User

class SearchActivity: AppCompatActivity(), SearchList.View, ClickListener {
    private lateinit var presenter: SearchList.Presenter
    private lateinit var adapter: SearchAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var loader: LoadingDialog
    private lateinit var searchView: SearchView
    private lateinit var toolbar: Toolbar
    private lateinit var emptyTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search)
        adapter = SearchAdapter(ArrayList(), this)
        adapter.clickListener = this
        val recyclerView: RecyclerView = findViewById(R.id.search_recycler_view)
        emptyTextView = findViewById(R.id.empty_users)
        recyclerView.adapter = adapter
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        setPresenter(SearchPresenter(this, DependencyInjectorImpl()))
        toolbar = findViewById(R.id.search_toolbar)
        addTollbarListener()
        loader = LoadingDialog(this)
        searchView = findViewById(R.id.search_user)
        addSearchViewListener()
        presenter.onViewCreated()
    }

    private fun addTollbarListener() {
        toolbar.setNavigationOnClickListener {
            presenter.backClicked()
        }
    }

    private fun addSearchViewListener() {

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

        searchView.setOnCloseListener {
            presenter.search(null)
            true
        }
    }

    override fun setPresenter(presenter: SearchList.Presenter) {
        this.presenter = presenter
    }

    override fun showHome() {
        finish()
    }

    override fun showUsers(data: List<User>) {
        emptyTextView.isVisible = data.isEmpty()
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

    override fun showLoader() {
        loader.startLoadingDialog()
    }

    override fun hideLoader() {
        loader.dismissDialog()
    }
}