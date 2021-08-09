package com.nchikvinidze.messengerapp.Login

interface ILoginPresenter {
    fun signInAttempt(nick : String, psw : String)
    fun notifyIncorrect()
    fun successfulLogin(nick : String)
    fun checkAlreadyLoggedIn()
    fun notifyLoggedIn(nick : String)
    fun notLoggedIn()
}