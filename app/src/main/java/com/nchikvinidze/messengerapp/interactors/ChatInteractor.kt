package com.nchikvinidze.messengerapp.interactors

import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
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

    fun uploadToDB(msg : MessageItem){
        if(auth.currentUser == null) auth.signInAnonymously()
        //todo upload message to db
    }

    fun downloadMessages(nick : String, otherNick : String){
        if(auth.currentUser == null) auth.signInAnonymously()
        val messagesRef = database.getReference("messages2")
        var childName = "$nick-$otherNick"
        /*messagesRef.child(childName).get().addOnSuccessListener {
            if(it.exists()){
                for(child in it.children){
                    //val sent = true

                }
            }
        }*/
    }
}