package com.nchikvinidze.messengerapp.Login

import android.content.SharedPreferences

class LoginPresenter(var view: ILoginView, sharedPref : SharedPreferences): ILoginPresenter {
    private val interactor = LoginInteractor(this, sharedPref)

    override fun signInAttempt(nick: String, psw: String) {
        view.showLoader()
        interactor.checkSignIn(nick, psw)
    }

    override fun notifyIncorrect() {
        view.hideLoader()
        view.notifyIncorrectCredentials()
    }

    override fun successfulLogin(nick : String) {
        view.hideLoader()
        view.moveToHome(nick)
    }

    override fun checkAlreadyLoggedIn() {
        view.showLoader()
        interactor.loggedInCheck()
    }

    override fun notifyLoggedIn(nick : String) {
        view.hideLoader()
        view.moveToHome(nick)
    }
}