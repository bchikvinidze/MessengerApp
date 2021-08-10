package com.nchikvinidze.messengerapp.Chat

import android.content.SharedPreferences
import com.nchikvinidze.messengerapp.data.MessageItem

class ChatPresenter(var view: IChatView) : IChatPresenter {
    private val interactor = ChatInteractor(this)

    override fun saveSentMessage(msg : MessageItem) {
        interactor.uploadToDB(msg)
    }

    override fun backClicked() {
        view.showHome()
    }
}