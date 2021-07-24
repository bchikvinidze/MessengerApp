package com.nchikvinidze.messengerapp.presenters

import com.nchikvinidze.messengerapp.Views.ILoginView
import com.nchikvinidze.messengerapp.interactors.LoginInteractor

class LoginPresenter(var view: ILoginView): ILoginPresenter {
    private val interactor = LoginInteractor(this)

    override fun signInAttempt(nick: String, psw: String) {
        interactor.checkSignIn(nick, psw)
    }

    override fun notifyIncorrect() {
        view.notifyIncorrectCredentials()
    }

    override fun successfulLogin(nick : String, psw : String) {
        view.moveToHome(nick, psw)
    }
}