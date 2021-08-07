package com.nchikvinidze.messengerapp.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nchikvinidze.messengerapp.DependencyInjectorImpl
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.Search.SearchAdapter
import com.nchikvinidze.messengerapp.data.User

class HomeFragment: Fragment(R.layout.home), HomeList.View {
    private lateinit var presenter: HomeList.Presenter
    private lateinit var adapter: SearchAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    var scrollListener: ScrollListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.home, container, false)
        recyclerView = root.findViewById(R.id.recycler_view)
        adapter = SearchAdapter(listOf(User("asd", "asd", "asdasd", null),User("asd", "asd", "asdasd", null),User("asd", "asd", "asdasd", null),User("asd", "asd", "asdasd", null),User("asd", "asd", "asdasd", null),User("asd", "asd", "asdasd", null),
            User("asd", "asd", "asdasd", null), User("asd", "asd", "asdasd", null)))
        recyclerView.adapter = adapter
        layoutManager = LinearLayoutManager(root.context)
        recyclerView.layoutManager = layoutManager
        setPresenter(HomePresenter(this, DependencyInjectorImpl()))
        setRecyclerViewScrollListener()
        return root
    }

    private fun setRecyclerViewScrollListener() {
        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    presenter.scrolledDown()
                } else if (dy < 0 ) {
                    presenter.scrolledUp()
                }
            }
        }
        recyclerView.addOnScrollListener(scrollListener)
    }

    override fun showBottomNav() {
        scrollListener?.showBottomNav()
    }

    override fun hideBottomNav() {
        scrollListener?.hideBottomNav()
    }

    override fun setPresenter(presenter: HomeList.Presenter) {
        this.presenter = presenter
    }
}

interface ScrollListener {
    fun showBottomNav()
    fun hideBottomNav()
}