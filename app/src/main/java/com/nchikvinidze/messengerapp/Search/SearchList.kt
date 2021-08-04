package com.nchikvinidze.messengerapp.Search

import com.nchikvinidze.messengerapp.BasePresenter
import com.nchikvinidze.messengerapp.BaseView

interface SearchList {
    interface Presenter : BasePresenter {
        fun backClicked()
    }

    interface View : BaseView<Presenter> {
        fun showHome()
    }
}