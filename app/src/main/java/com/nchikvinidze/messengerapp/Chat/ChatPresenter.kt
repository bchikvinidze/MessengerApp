package com.nchikvinidze.messengerapp.Chat

import android.content.SharedPreferences
import com.nchikvinidze.messengerapp.data.MessageItem

class ChatPresenter(var view: IChatView, sharedPref : SharedPreferences) : IChatPresenter {
    private val interactor = ChatInteractor(this, sharedPref)

    override fun saveSentMessage(msg : MessageItem) {
        interactor.uploadToDB(msg)
    }

    override fun showMessageHistory(nick: String, otherNick: String) {
        interactor.downloadMessages(nick, otherNick)
    }

    override fun displayDownloadedMessage(msg: MessageItem) {
        view.displayMessage(msg)
    }

    override fun displayDownloadedMessageList(lst: List<MessageItem>) {
        for(elem in lst){
            view.displayMessage(elem)
        }
    }
}