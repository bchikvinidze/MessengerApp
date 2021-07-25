package com.nchikvinidze.messengerapp.interactors

import android.content.SharedPreferences
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.nchikvinidze.messengerapp.presenters.interfaces.ILoginPresenter

class LoginInteractor(val presenter: ILoginPresenter, sharedPref : SharedPreferences)  {
    val database = Firebase.database
    val storage = Firebase.storage
    val auth = Firebase.auth
    var sharedPreferences = sharedPref

    fun checkSignIn(nick: String, psw: String){
        if(auth.currentUser == null) auth.signInAnonymously()
        val usersRef = database.getReference("users")
        //check if user exists
        userExists(nick, psw, usersRef)
    }

    fun userExists(nick : String, psw : String, usersRef : DatabaseReference){
        usersRef.child(nick).get().addOnSuccessListener {
            if(it.exists()){ // es it ar gamodis
                with (sharedPreferences.edit()) {
                    putBoolean(LOGGED_ON, true)
                    putString(LOGGED_NICKNAME, nick)
                    apply()
                }
                presenter.successfulLogin(nick)
            } else {
                presenter.notifyIncorrect()
            }
        }
        //var userItem = usersRef.child("users").child(nick).get()
        //if(userItem.result)
    }

    fun loggedInCheck(){
        if(sharedPreferences.getBoolean(LOGGED_ON, false)){
            var nickname = sharedPreferences.getString(LOGGED_NICKNAME, "Error").toString()
            presenter.notifyLoggedIn(nickname)
        }
    }

    companion object{
        val LOGGED_ON = "LOGGED_ON"
        val LOGGED_NICKNAME = "LOGGED_NICKNAME"
    }
}