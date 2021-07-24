package com.nchikvinidze.messengerapp.presenters

interface ILoginPresenter {
    fun signInAttempt(nick : String, psw : String)
}