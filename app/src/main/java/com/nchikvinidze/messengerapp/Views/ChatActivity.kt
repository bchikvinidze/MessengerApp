package com.nchikvinidze.messengerapp.Views

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.nchikvinidze.messengerapp.ChatItemsAdapter
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.Views.Interfaces.IChatView
import com.nchikvinidze.messengerapp.data.MessageItem
import com.nchikvinidze.messengerapp.presenters.ChatPresenter
import com.nchikvinidze.messengerapp.presenters.LoginPresenter
import java.text.SimpleDateFormat
import java.util.*

class ChatActivity : AppCompatActivity(), IChatView{

    lateinit var chatrv : RecyclerView
    lateinit var chatrvAdapter : ChatItemsAdapter
    lateinit var editMessage : EditText
    lateinit var sendButton : ImageButton
    lateinit var sharedPref : SharedPreferences
    lateinit var presenter : ChatPresenter
    lateinit var chatappbar : AppBarLayout
    override lateinit var nick: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chatpage)
        chatrv = findViewById(R.id.chatRv)
        editMessage = findViewById(R.id.editMessage)
        sendButton = findViewById(R.id.sendButton)
        chatappbar = findViewById(R.id.chatappbar)
        sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        chatrvAdapter = ChatItemsAdapter()
        chatrv.adapter = chatrvAdapter
        chatrv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        presenter = ChatPresenter(this, sharedPref)
        nick = intent.getStringExtra("username").toString()
        //aq mgoni load screen daschirdeba rom chavamatot
        //presenter.getCurrentNickname()

        editMessage.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                chatappbar.setExpanded(false)
            } else {
                chatappbar.setExpanded(true)
            }
        }

        sendButton.setOnClickListener {
            presenter.saveSentMessage()
            displaySentMessage()
        }
    }

    fun displaySentMessage(){
        val sdf = SimpleDateFormat("HH:mm")
        val time = sdf.format(Date()).toString()
        var msg = MessageItem(time, true, nick, editMessage.text.toString())
        chatrvAdapter.list.add(msg)
        chatrvAdapter.notifyDataSetChanged()
        chatrv.scrollToPosition(chatrvAdapter.itemCount - 1)
        editMessage.setText("")
    }
}