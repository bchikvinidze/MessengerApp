package com.nchikvinidze.messengerapp.Home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nchikvinidze.messengerapp.DependencyInjectorImpl
import com.nchikvinidze.messengerapp.R

class HomeActivity: AppCompatActivity(), HomeList.View {
    private lateinit var presenter: HomeList.Presenter
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        setPresenter(HomePresenter(this, DependencyInjectorImpl()))
    }

    override fun setPresenter(presenter: HomeList.Presenter) {
        this.presenter = presenter
    }
}