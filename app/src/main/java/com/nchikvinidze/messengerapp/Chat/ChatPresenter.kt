package com.nchikvinidze.messengerapp.Chat

import android.content.SharedPreferences
import com.nchikvinidze.messengerapp.data.MessageItem
import java.net.URL

class ChatPresenter(var view: IChatView) : IChatPresenter {
    private val interactor = ChatInteractor(this)

    override fun saveSentMessage(msg : MessageItem) {
        interactor.uploadToDB(msg)
    }

    override fun backClicked() {
        view.showHome()
    }

    override fun setupImage(url: URL) {
        view?.showImage(url)
    }

    override fun setupProffession(profesion: String) {
        view?.showProffession(profesion)
    }

    override fun loadData(user: String) {
        interactor.getProfileInfo(user)
    }
}