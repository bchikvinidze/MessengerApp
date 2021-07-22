package com.nchikvinidze.messengerapp

import android.graphics.drawable.Drawable

class MainPresenter(var view: IMainView): IMainPresenter {
    private val interactor = MainInteractor(this)
    fun saveNewUser(nick : String, psw : String, prof: String, img : Drawable){
        interactor.newUserToDb(nick, psw, prof, img)
    }
}