package com.nchikvinidze.messengerapp.Chat

import android.content.SharedPreferences
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.nchikvinidze.messengerapp.data.MessageItem

class ChatInteractor(val presenter: IChatPresenter, sharedPref : SharedPreferences)   {
    val database = Firebase.database
    val storage = Firebase.storage
    val auth = Firebase.auth
    var sharedPreferences = sharedPref

    fun uploadToDB(msg : MessageItem){
        if(auth.currentUser == null) auth.signInAnonymously()
        val messagesRef = database.getReference(MESSAGES)

        var childName = msg.from + "-" + msg.to
        var childNameReversed = msg.to + "-" + msg.from
        messagesRef.push().key?.let {
            messagesRef.child(childName).child(it).setValue(msg)
            var msg2 = MessageItem(msg.timemillis, msg.time, !msg.sent, msg.from, msg.to, msg.text)
            messagesRef.child(childNameReversed).child(it).setValue(msg2)
        }
    }

    fun downloadMessages(nick : String, otherNick : String){
        if(auth.currentUser == null) auth.signInAnonymously()
        val messagesRef = database.getReference(MESSAGES)
        var childName = "$nick-$otherNick"

        messagesRef.child(childName).get().addOnSuccessListener {
            if(it.exists()){
                var messagesList = ArrayList<MessageItem>()
                for(child in it.children){
                    var from = child.child(FROM).getValue<String>().toString()
                    var to = child.child(TO).getValue<String>().toString()
                    var text = child.child(TEXT).getValue<String>().toString()
                    var time = child.child(TIME).getValue<String>().toString()
                    var timemillis = child.child(TIMEMILLIS).getValue<Long>()?.toLong()
                    if(timemillis == null) timemillis = 0
                    if(from == nick && otherNick == to)
                        messagesList.add(MessageItem(timemillis, time, true, nick, otherNick, text))
                    else
                        messagesList.add(MessageItem(timemillis, time, false, otherNick, nick, text))
                }
                var sortedList = messagesList.sortedWith(compareBy({ it.timemillis }))
                presenter.displayDownloadedMessageList(sortedList)
            }
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