package com.nchikvinidze.messengerapp.Home

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.nchikvinidze.messengerapp.Chat.ChatInteractor
import com.nchikvinidze.messengerapp.data.MessageItem
import com.nchikvinidze.messengerapp.prefs
import java.net.URL

class HomeInteractor(val presenter: HomeList.Presenter) {
    val database = Firebase.database
    val auth = Firebase.auth
    val storage = Firebase.storage

    fun loadMessageList(){
        if(auth.currentUser == null) auth.signInAnonymously()
        val messagesRef = database.getReference(ChatInteractor.MESSAGES)
        val nick = prefs.userName ?: ""
        var childName = "$nick"

        messagesRef.child(childName).get().addOnSuccessListener {
            if(it.exists()){
                var messagesList: MutableList<MessageItem> = mutableListOf()
                for(child in it.children) {
                    val message = child.children.last()
                    var from = message.child(ChatInteractor.FROM).getValue<String>().toString()
                    var to = message.child(ChatInteractor.TO).getValue<String>().toString()
                    var text = message.child(ChatInteractor.TEXT).getValue<String>().toString()
                    var time = message.child(ChatInteractor.TIME).getValue<String>().toString()
                    var timemillis =
                        message.child(ChatInteractor.TIMEMILLIS).getValue<Long>()?.toLong()
                    if (timemillis == null) timemillis = 0
                    var item = MessageItem(timemillis, time, false, from, nick, text)
                    if (from == nick)
                        item = MessageItem(timemillis, time, true, nick, to, text)
                    messagesList.add(item)
                }
                var sortedList = messagesList.sortedWith(compareBy({ it.timemillis }))
                presenter.messagesLoaded(sortedList.toMutableList())
            } else {
                presenter.messagesLoaded(ArrayList())
            }
        }
    }

    private fun addMessage(snapshot: DataSnapshot) {
        val nick = prefs.userName ?: ""
        if(snapshot.exists()){
            val message = snapshot.children.last()
            var from = message.child(ChatInteractor.FROM).getValue<String>().toString()
            var to = message.child(ChatInteractor.TO).getValue<String>().toString()
            var text = message.child(ChatInteractor.TEXT).getValue<String>().toString()
            var time = message.child(ChatInteractor.TIME).getValue<String>().toString()
            var timemillis = message.child(ChatInteractor.TIMEMILLIS).getValue<Long>()?.toLong()
            if(timemillis == null) timemillis = 0
            var item = MessageItem(timemillis, time, false, from, nick, text)
            if(from == nick)
                item = MessageItem(timemillis, time, true, nick, to, text)
            val otherNick = if(prefs.userName == item.from) item.to else item.from
            val imgRef = storage.getReference(otherNick)
            imgRef.downloadUrl.addOnSuccessListener {
                item.url = URL(it.toString())
                presenter.addItem(item)
            }
        }
    }

    fun addListener() {
        val nick = prefs.userName ?: ""
        val ref = database.getReference(ChatInteractor.MESSAGES).child("$nick")
        ref.addChildEventListener(object: ChildEventListener {
            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                addMessage(p0)
            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                addMessage(p0)
            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }
            override fun onChildRemoved(p0: DataSnapshot) {

            }
            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    fun getUrl(item: MessageItem) {
        val otherNick = if(prefs.userName == item.from) item.to else item.from
        val imgRef = storage.getReference(otherNick)
        imgRef.downloadUrl.addOnSuccessListener {
            item.url = URL(it.toString())
        }
    }
}