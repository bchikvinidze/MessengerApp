package com.nchikvinidze.messengerapp.presenters

interface ILoginPresenter {
    fun signInAttempt(nick : String, psw : String)
    fun notifyIncorrect()
    fun successfulLogin(nick : String, psw : String)
}