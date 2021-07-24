package com.nchikvinidze.messengerapp.presenters

import android.graphics.drawable.Drawable
import com.nchikvinidze.messengerapp.interactors.SignupInteractor
import com.nchikvinidze.messengerapp.Views.ISignupView

class SignupPresenter(var view: ISignupView): ISignupPresenter {
    private val interactor = SignupInteractor(this)
    fun saveNewUser(nick : String, psw : String, prof: String, img : Drawable){
        interactor.newUserToDb(nick, psw, prof, img)
    }
    override fun notifyUserExists(){
        view.userExists()
    }

    override fun moveToSignin() {
        view.moveToSignIn()
    }
}