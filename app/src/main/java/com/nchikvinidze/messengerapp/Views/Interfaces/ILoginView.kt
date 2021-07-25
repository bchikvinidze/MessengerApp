package com.nchikvinidze.messengerapp.Views.Interfaces

interface ILoginView {
    fun notifyIncorrectCredentials()
    fun moveToHome(nick : String)
}