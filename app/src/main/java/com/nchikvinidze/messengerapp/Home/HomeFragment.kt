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

class HomeFragment: Fragment(R.layout.home), HomeList.View {
    private lateinit var presenter: HomeList.Presenter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.home, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.recycler_view)
        layoutManager = LinearLayoutManager(root.context)
        recyclerView.layoutManager = layoutManager
        setPresenter(HomePresenter(this, DependencyInjectorImpl()))
        return root
    }

    override fun setPresenter(presenter: HomeList.Presenter) {
        this.presenter = presenter
    }
}