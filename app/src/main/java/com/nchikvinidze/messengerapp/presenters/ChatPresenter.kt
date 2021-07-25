package com.nchikvinidze.messengerapp.presenters

import android.content.SharedPreferences
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.nchikvinidze.messengerapp.Views.Interfaces.IChatView
import com.nchikvinidze.messengerapp.interactors.ChatInteractor
import com.nchikvinidze.messengerapp.presenters.interfaces.IChatPresenter

class ChatPresenter(var view: IChatView, sharedPref : SharedPreferences) : IChatPresenter {
    private val interactor = ChatInteractor(this, sharedPref)

    override fun setCurrentNickname(nick: String) {
        view.nick = nick
    }

    override fun saveSentMessage() {
        TODO("Not yet implemented")
    }
}