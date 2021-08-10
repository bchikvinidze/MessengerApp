package com.nchikvinidze.messengerapp.Chat

import android.content.SharedPreferences
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.nchikvinidze.messengerapp.data.MessageItem

class ChatInteractor(val presenter: IChatPresenter)   {
    val database = Firebase.database
    val storage = Firebase.storage
    val auth = Firebase.auth

    fun uploadToDB(msg : MessageItem){
        if(auth.currentUser == null) auth.signInAnonymously()
        val messagesRef = database.getReference(MESSAGES)

        messagesRef.push().key?.let {
            messagesRef.child(msg.from).child(msg.to).child(it).setValue(msg)
            var msg2 = MessageItem(msg.timemillis, msg.time, !msg.sent, msg.from, msg.to, msg.text)
            messagesRef.child(msg.to).child(msg.from).child(it).setValue(msg2)
        }
    }

    companion object{
        val FROM = "from"
        val TO = "to"
        val TEXT = "text"
        val TIME = "time"
        val TIMEMILLIS = "timemillis"
        val MESSAGES = "messages2"
    }
}