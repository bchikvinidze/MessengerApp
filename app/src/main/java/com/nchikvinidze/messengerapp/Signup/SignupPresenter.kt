package com.nchikvinidze.messengerapp.Signup

import android.graphics.drawable.Drawable
import com.nchikvinidze.messengerapp.Signup.SignupInteractor
import com.nchikvinidze.messengerapp.Signup.ISignupView
import com.nchikvinidze.messengerapp.Signup.ISignupPresenter

class SignupPresenter(var view: ISignupView): ISignupPresenter {
    private val interactor = SignupInteractor(this)

    fun saveNewUser(nick : String, psw : String, prof: String, img : Drawable){
        view.showLoader()
        interactor.newUserToDb(nick, psw, prof, img)
    }
    override fun notifyUserExists(){
        view.hideLoader()
        view.userExists()
    }

    override fun moveToSignin() {
        view.hideLoader()
        view.moveToSignIn()
    }
}