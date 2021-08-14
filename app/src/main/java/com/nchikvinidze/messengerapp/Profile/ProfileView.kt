package com.nchikvinidze.messengerapp.Profile

import android.graphics.drawable.Drawable
import com.nchikvinidze.messengerapp.BasePresenter
import com.nchikvinidze.messengerapp.BaseView

interface ProfileView {
    interface Presenter : BasePresenter {
        fun signOut()
        fun getProfileInfo()
        fun setupProfileProfession(nick: String, profession : String)
        fun setupProfileImage(url : String)
        fun updateUser(newProf : String, newImage : Drawable)
        fun showError()
    }

    interface View : BaseView<Presenter> {
        fun showLoader()
        fun hideLoader()
        fun showLogin()
        fun setupProfileProfession(nick: String, profession : String)
        fun setupProfileImage(url : String)
        fun showError()
    }
}