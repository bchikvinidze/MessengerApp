package com.nchikvinidze.messengerapp.Home

import com.nchikvinidze.messengerapp.BasePresenter
import com.nchikvinidze.messengerapp.BaseView
import com.nchikvinidze.messengerapp.data.MessageItem

interface HomeList {
    interface Presenter : BasePresenter {
        fun scrolledDown()
        fun scrolledUp()
        fun loadMessages()
        fun messagesLoaded(data: MutableList<MessageItem>)
        fun addItem(item: MessageItem)
        fun onViewCreated()
        fun search(query: String?)
        fun showError()
    }

    interface View : BaseView<Presenter> {
        fun showBottomNav()
        fun hideBottomNav()
        fun showMessages(data: List<MessageItem>)
        fun showLoader()
        fun hideLoader()
        fun showError()
    }
}