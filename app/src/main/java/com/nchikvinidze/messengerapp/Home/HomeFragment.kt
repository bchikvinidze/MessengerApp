package com.nchikvinidze.messengerapp.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.nchikvinidze.messengerapp.Chat.ChatInteractor
import com.nchikvinidze.messengerapp.DependencyInjectorImpl
import com.nchikvinidze.messengerapp.LoadingDialog.LoadingDialog
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.data.MessageItem
import com.nchikvinidze.messengerapp.data.MessageList
import com.nchikvinidze.messengerapp.prefs

class HomeFragment: Fragment(R.layout.home), HomeList.View {
    private lateinit var presenter: HomeList.Presenter
    private lateinit var adapter: HomeAdapter
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var recyclerView: RecyclerView
    var scrollListener: ScrollListener? = null
    private lateinit var loader: LoadingDialog
    var clickListener: MessageClickListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.home, container, false)
        recyclerView = root.findViewById(R.id.recycler_view)
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

    override fun showMessages(data: List<MessageItem>) {
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