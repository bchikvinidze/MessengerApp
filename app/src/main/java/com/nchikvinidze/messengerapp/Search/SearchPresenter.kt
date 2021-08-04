package com.nchikvinidze.messengerapp.Search

import com.nchikvinidze.messengerapp.DependencyInjector

class SearchPresenter(view: SearchList.View,
                    dependencyInjector: DependencyInjector
) : SearchList.Presenter {

    private var view: SearchList.View? = view

    override fun onDestroy() {
        this.view = null
    }

    override fun backClicked() {
        view?.showHome()
    }
}