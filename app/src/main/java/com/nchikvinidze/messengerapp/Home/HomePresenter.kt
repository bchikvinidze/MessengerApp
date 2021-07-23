package com.nchikvinidze.messengerapp.Home

import com.nchikvinidze.messengerapp.DependencyInjector

class HomePresenter(view: HomeList.View,
                    dependencyInjector: DependencyInjector
) : HomeList.Presenter {

    private var view: HomeList.View? = view

    override fun onDestroy() {
        this.view = null
    }

}
