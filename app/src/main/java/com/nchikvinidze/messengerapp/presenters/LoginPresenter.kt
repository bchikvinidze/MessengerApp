package com.nchikvinidze.messengerapp.presenters

import android.graphics.drawable.Drawable
import com.nchikvinidze.messengerapp.Views.ILoginView
import com.nchikvinidze.messengerapp.MainInteractor

class LoginPresenter(var view: ILoginView): ILoginPresenter {
    private val interactor = MainInteractor(this)
    fun saveNewUser(nick : String, psw : String, prof: String, img : Drawable){
        interactor.newUserToDb(nick, psw, prof, img)
    }
}