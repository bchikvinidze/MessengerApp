package com.nchikvinidze.messengerapp.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nchikvinidze.messengerapp.DependencyInjectorImpl
import com.nchikvinidze.messengerapp.LoadingDialog.LoadingDialog
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.data.MessageItem

class HomeFragment: Fragment(R.layout.home), HomeList.View {
    private lateinit var presenter: HomeList.Presenter
    private lateinit var adapter: HomeAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    var scrollListener: ScrollListener? = null
    private lateinit var loader: LoadingDialog
    var clickListener: MessageClickListener? = null
    private lateinit var searchView: SearchView
    private lateinit var emptyTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.home, container, false)
        recyclerView = root.findViewById(R.id.recycler_view)
        searchView = root.findViewById(R.id.home_search)
        emptyTextView = root.findViewById(R.id.empty_home)
        adapter = HomeAdapter(ArrayList(), root.context)
        adapter.clickListener = clickListener
        recyclerView.adapter = adapter
        layoutManager = LinearLayoutManager(root.context)
        recyclerView.layoutManager = layoutManager
        loader = activity?.let { LoadingDialog(it) }!!
        setPresenter(HomePresenter(this, DependencyInjectorImpl()))
        setRecyclerViewScrollListener()
        presenter.onViewCreated()
        presenter.loadMessages()
        addSearchViewListener()
        return root
    }

    private fun addSearchViewListener() {

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.length ?: 0 >= 3 ) {
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

    override fun showMessages(data: List<MessageItem>) {
        emptyTextView.isVisible = data.isEmpty()
        adapter.update(data)
    }

    override fun showLoader() {
        loader.startLoadingDialog()
    }

    override fun hideLoader() {
        loader.dismissDialog()
    }
}

interface ScrollListener {
    fun showBottomNav()
    fun hideBottomNav()
}