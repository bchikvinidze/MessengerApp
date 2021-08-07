package com.nchikvinidze.messengerapp.Profile

import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.net.Uri
import com.nchikvinidze.messengerapp.Signup.ISignupView

class ProfilePresenter(var view: ProfileFragment) {
    var interactor = ProfileInteractor(this)

    fun updateUser(nick : String, newProf : String, newImage : Drawable){
        interactor.updateUser(nick, newProf, newImage)
    }

    fun signOut(){
        interactor.signOut()
    }

    fun getProfileInfo(nick: String){
        interactor.getProfileInfo(nick)
    }

    fun setupProfileProfession(profession : String){
        view.setupProfileProfession(profession)
    }

    fun setupProfileImage(url : String){
        view.setupProfileImage(url)
    }
}