package com.nchikvinidze.messengerapp.Search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.nchikvinidze.messengerapp.DependencyInjectorImpl
import com.nchikvinidze.messengerapp.R

class SearchActivity: AppCompatActivity(), SearchList.View {
    private lateinit var presenter: SearchList.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search)
        setPresenter(SearchPresenter(this, DependencyInjectorImpl()))
        val toolbar: Toolbar = findViewById(R.id.search_toolbar)
        toolbar.setNavigationOnClickListener {
            presenter.backClicked()
        }
    }

    override fun setPresenter(presenter: SearchList.Presenter) {
        this.presenter = presenter
    }

    override fun showHome() {
        finish()
    }
}