package com.nchikvinidze.messengerapp.Login

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.nchikvinidze.messengerapp.prefs

class LoginInteractor(val presenter: ILoginPresenter)  {
    val database = Firebase.database
    val storage = Firebase.storage
    val auth = Firebase.auth

    fun checkSignIn(nick: String, psw: String){
        if(auth.currentUser == null) {
            auth.signInWithEmailAndPassword(nick, psw)
        }
        val usersRef = database.getReference("users")
        userExists(nick, psw, usersRef)
    }

    fun userExists(nick : String, psw : String, usersRef : DatabaseReference){
        usersRef.child(nick).get().addOnSuccessListener {
            if(it.exists()){ // es it ar gamodis
                prefs.userName = nick
                presenter.successfulLogin(nick)
            } else {
                presenter.notifyIncorrect()
            }
        }
    }

    fun loggedInCheck(){
        if(prefs.userName != null){
            var nickname = prefs.userName ?: ""
            presenter.notifyLoggedIn(nickname)
        } else {
            presenter.notLoggedIn()
        }
    }
}