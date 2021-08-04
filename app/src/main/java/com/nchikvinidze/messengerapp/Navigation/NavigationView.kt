package com.nchikvinidze.messengerapp.Navigation

import com.nchikvinidze.messengerapp.BasePresenter
import com.nchikvinidze.messengerapp.BaseView

interface NavigationView {
    interface Presenter : BasePresenter {
        fun onViewCreated()
        fun homeClicked()
        fun profileClicked()
        fun fabClicked()
    }

    interface View : BaseView<Presenter> {
        fun showHome()
        fun showProfile()
        fun showSearch()
    }
}