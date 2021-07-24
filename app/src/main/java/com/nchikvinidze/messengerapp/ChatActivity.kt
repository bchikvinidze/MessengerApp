package com.nchikvinidze.messengerapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class ChatActivity : AppCompatActivity(){

    lateinit var chatrv : RecyclerView
    lateinit var chatrvAdapter : ChatItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chatpage)
        chatrv = findViewById(R.id.chatRv)
        chatrvAdapter = ChatItemsAdapter()
        chatrv.adapter = chatrvAdapter

    }
}