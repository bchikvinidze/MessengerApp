package com.nchikvinidze.messengerapp.Login

interface ILoginView {
    fun notifyIncorrectCredentials()
    fun moveToHome(nick : String)
    fun showLoader()
    fun hideLoader()
}