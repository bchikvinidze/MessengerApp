package com.nchikvinidze.messengerapp.Home

import com.nchikvinidze.messengerapp.BasePresenter
import com.nchikvinidze.messengerapp.BaseView

interface HomeList {
    interface Presenter : BasePresenter {
        fun scrolledDown()
        fun scrolledUp()
    }

    interface View : BaseView<Presenter> {
        fun showBottomNav()
        fun hideBottomNav()
    }
}