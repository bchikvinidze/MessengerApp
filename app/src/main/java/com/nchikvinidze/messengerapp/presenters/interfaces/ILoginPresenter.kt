package com.nchikvinidze.messengerapp.presenters.interfaces

interface ILoginPresenter {
    fun signInAttempt(nick : String, psw : String)
    fun notifyIncorrect()
    fun successfulLogin(nick : String)
    fun checkAlreadyLoggedIn()
    fun notifyLoggedIn(nick : String)
}