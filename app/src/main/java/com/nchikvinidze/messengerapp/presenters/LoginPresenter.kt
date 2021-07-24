package com.nchikvinidze.messengerapp.presenters

import com.nchikvinidze.messengerapp.Views.ILoginView
import com.nchikvinidze.messengerapp.interactors.LoginInteractor
import com.nchikvinidze.messengerapp.interactors.SignupInteractor

class LoginPresenter(var view: ILoginView): ILoginPresenter {
    private val interactor = LoginInteractor(this)
    override fun signInAttempt(nick: String, psw: String) {
        
    }
}