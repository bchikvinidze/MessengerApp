package com.nchikvinidze.messengerapp.Search

import com.nchikvinidze.messengerapp.BasePresenter
import com.nchikvinidze.messengerapp.BaseView
import com.nchikvinidze.messengerapp.data.User

interface SearchList {
    interface Presenter : BasePresenter {
        fun backClicked()
        fun dataLoaded(data: List<User>)
        fun onViewCreated()
        fun search(name: String?)
        fun userClicked(user: User)
    }

    interface View : BaseView<Presenter> {
        fun showHome()
        fun showUsers(data: List<User>)
        fun showChat(user: User)
        fun showLoader()
        fun hideLoader()
    }
}