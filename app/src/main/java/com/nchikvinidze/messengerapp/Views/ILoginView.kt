package com.nchikvinidze.messengerapp.Views

interface ILoginView {
    fun notifyIncorrectCredentials()
    fun moveToHome(nick : String, psw : String)
}