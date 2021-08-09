package com.nchikvinidze.messengerapp.Signup

interface ISignupView {
    fun userExists()
    fun moveToSignIn()
    fun showLoader()
    fun hideLoader()
}