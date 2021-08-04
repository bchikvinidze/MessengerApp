package com.nchikvinidze.messengerapp.Navigation

import com.nchikvinidze.messengerapp.DependencyInjector

class NavigationPresenter(view: NavigationView.View,
                      dependencyInjector: DependencyInjector
) : NavigationView.Presenter {

    private var view: NavigationView.View? = view

    override fun onDestroy() {
        this.view = null
    }

    override fun onViewCreated() {
        view?.showHome()
    }

    override fun homeClicked() {
        view?.showHome()
    }

    override fun profileClicked() {
        view?.showProfile()
    }

    override fun fabClicked() {
        view?.showSearch()
    }
}