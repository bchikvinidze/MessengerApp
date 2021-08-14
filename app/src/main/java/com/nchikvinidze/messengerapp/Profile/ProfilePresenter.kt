package com.nchikvinidze.messengerapp.Profile

import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.net.Uri
import com.nchikvinidze.messengerapp.DependencyInjector
import com.nchikvinidze.messengerapp.Home.HomeList
import com.nchikvinidze.messengerapp.Signup.ISignupView

class ProfilePresenter(view: ProfileView.View,
                       dependencyInjector: DependencyInjector
): ProfileView.Presenter {
    var interactor = ProfileInteractor(this)

    private var view: ProfileView.View? = view

    override fun updateUser(newProf : String, newImage : Drawable){
        view?.showLoader()
        interactor.updateUser(newProf, newImage)
    }

    override fun signOut(){
        interactor.signOut()
        view?.showLogin()
    }

    override fun getProfileInfo(){
        interactor.getProfileInfo()
    }

    override fun setupProfileProfession(nick: String, profession : String){
        view?.hideLoader()
        view?.setupProfileProfession(nick, profession)
    }

    override fun setupProfileImage(url : String){
        view?.setupProfileImage(url)
    }

    override fun onDestroy() {
        this.view = null
    }

    override fun showError() {
        view?.showError()
    }
}