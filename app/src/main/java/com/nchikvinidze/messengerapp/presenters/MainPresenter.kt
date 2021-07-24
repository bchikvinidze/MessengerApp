package com.nchikvinidze.messengerapp.presenters

import android.graphics.drawable.Drawable
import com.nchikvinidze.messengerapp.Views.IMainView
import com.nchikvinidze.messengerapp.MainInteractor

class MainPresenter(var view: IMainView): IMainPresenter {
    private val interactor = MainInteractor(this)
    fun saveNewUser(nick : String, psw : String, prof: String, img : Drawable){
        interactor.newUserToDb(nick, psw, prof, img)
    }
}