package com.nchikvinidze.messengerapp.interactors

import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.nchikvinidze.messengerapp.data.MessageItem
import com.nchikvinidze.messengerapp.presenters.interfaces.IChatPresenter

class ChatInteractor(val presenter: IChatPresenter, sharedPref : SharedPreferences)   {
    val database = Firebase.database
    val storage = Firebase.storage
    val auth = Firebase.auth
    var sharedPreferences = sharedPref

   /* fun curUser(){
        if(sharedPreferences.getBoolean(LoginInteractor.LOGGED_ON, false)){
            var nickname = sharedPreferences.getString(LoginInteractor.LOGGED_NICKNAME, "Error").toString()
            presenter.setCurrentNickname(nickname)
        } else {
            Log.e("user error", "does not exist")
        }
    } */

    fun uploadToDB(msg : MessageItem){
        if(auth.currentUser == null) auth.signInAnonymously()
        //todo upload message to db
    }

    fun downloadMessages(nick : String, otherNick : String){
        if(auth.currentUser == null) auth.signInAnonymously()
        val usersRef = database.getReference("messages")
        usersRef.child("nick").get().addOnSuccessListener {
            if(it.exists()){

            } else {

            }
        }
    }
}