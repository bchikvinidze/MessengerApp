package com.nchikvinidze.messengerapp.interactors

import android.content.SharedPreferences
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
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

        /*messagesRef.push().key?.let {
            var nextId = 1 // timeinmillis mixedvit sjobs davalago albat...
            messagesRef.child(childName).child(nextId.toString()).setValue( MessageItem(0, "00:00", true, nick, otherNick, "ver gevige"))
        }*/

        messagesRef.child(childName).get().addOnSuccessListener {
            if(it.exists()){
                var messagesList = ArrayList<MessageItem>()
                for(child in it.children){
                    var from = child.child("from").getValue<String>().toString()
                    var to = child.child("to").getValue<String>().toString()
                    var text = child.child("text").getValue<String>().toString()
                    var time = child.child("time").getValue<String>().toString()
                    var timemillis = child.child("timemillis").getValue<Long>()?.toLong()
                    if(timemillis == null) timemillis = 0
                    if(from == nick && otherNick == to)
                        messagesList.add(MessageItem(timemillis, time, true, nick, otherNick, text))
                    else
                        messagesList.add(MessageItem(timemillis, time, false, otherNick, nick, text))
                }
                presenter.displayDownloadedMessageList(messagesList)
            }
        }
    }
}