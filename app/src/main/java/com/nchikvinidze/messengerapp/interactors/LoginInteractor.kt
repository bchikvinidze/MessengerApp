package com.nchikvinidze.messengerapp.interactors

import android.graphics.drawable.Drawable
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.nchikvinidze.messengerapp.presenters.ILoginPresenter

class LoginInteractor(val presenter: ILoginPresenter)  {
    val database = Firebase.database
    val storage = Firebase.storage
    val auth = Firebase.auth

    fun checkSignIn(nick: String, psw: String){
        if(auth.currentUser == null) auth.signInAnonymously()
        val usersRef = database.getReference("users")
        //check if user exists
        userExists(nick, psw, usersRef)
    }

    fun userExists(nick : String, psw : String, usersRef : DatabaseReference){
        usersRef.child("users").child(nick).get().addOnSuccessListener {
            if(it.exists()){
                presenter.successfulLogin(nick, psw)
            } else {
                presenter.notifyIncorrect()
            }
        }
    }

}