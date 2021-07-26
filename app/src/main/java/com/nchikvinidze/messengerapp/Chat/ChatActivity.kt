package com.nchikvinidze.messengerapp.Chat

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.appbar.AppBarLayout
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.nchikvinidze.messengerapp.ChatItemsAdapter
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.data.MessageItem
import java.text.SimpleDateFormat
import java.util.*

class ChatActivity : AppCompatActivity(), IChatView {

    lateinit var chatrv : RecyclerView
    lateinit var chatrvAdapter : ChatItemsAdapter
    lateinit var editMessage : EditText
    lateinit var sendButton : ImageButton
    lateinit var sharedPref : SharedPreferences
    lateinit var presenter : ChatPresenter
    lateinit var chatappbar : AppBarLayout
    lateinit var nick: String
    lateinit var otherNick: String
    //maybe Ill move this someplace else later....

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chatpage)
        chatrv = findViewById(R.id.chatRv)
        editMessage = findViewById(R.id.editMessage)
        sendButton = findViewById(R.id.sendButton)
        chatappbar = findViewById(R.id.chatappbar)
        sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        nick = intent.getStringExtra("nickname").toString()
        otherNick = intent.getStringExtra("recipient").toString()

        //IUshi ar undaiyos mara droebit davtoveb adapteris dasetvas
        val options = FirebaseRecyclerOptions.Builder<MessageItem>()
            .setQuery(Firebase.database.getReference(ChatInteractor.MESSAGES+"/$nick-$otherNick"), MessageItem::class.java)
            .setLifecycleOwner(this)
            .build()
        chatrvAdapter = ChatItemsAdapter(options)
        chatrv.adapter = chatrvAdapter
        chatrv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        presenter = ChatPresenter(this, sharedPref)
        //aq mgoni load screen daschirdeba rom chavamatot
        presenter.showMessageHistory(nick, otherNick)

        editMessage.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                chatappbar.setExpanded(false)
            } else {
                chatappbar.setExpanded(true)
            }
        }

        sendButton.setOnClickListener {
            val sdf = SimpleDateFormat("HH:mm")
            val time = sdf.format(Date()).toString()
            var cal = Calendar.getInstance()
            var msg = MessageItem(cal.timeInMillis, time,true, nick, otherNick, editMessage.text.toString())
            //chatrvAdapter.list.add(msg)
            chatrvAdapter.notifyDataSetChanged()
            presenter.saveSentMessage(msg) // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            afterMessageDisplay()
        }
    }

    override fun displayMessage(msg: MessageItem){
        //chatrvAdapter.list.add(msg)
        chatrvAdapter.notifyDataSetChanged()
        afterMessageDisplay()
    }

    fun afterMessageDisplay(){
        chatrv.scrollToPosition(chatrvAdapter.itemCount)
        editMessage.setText("")
    }

    override fun onStart() {
        super.onStart()
        chatrvAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        chatrvAdapter.stopListening()
    }
}