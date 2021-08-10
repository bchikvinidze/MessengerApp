package com.nchikvinidze.messengerapp.Chat

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.SubtitleCollapsingToolbarLayout
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.nchikvinidze.messengerapp.ChatItemsAdapter
import com.nchikvinidze.messengerapp.LoadingDialog.LoadingDialog
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.data.MessageItem
import java.text.SimpleDateFormat
import java.util.*

class ChatActivity : AppCompatActivity(), IChatView {

    private lateinit var chatrv : RecyclerView
    private lateinit var chatrvAdapter : ChatItemsAdapter
    private lateinit var editMessage : EditText
    private lateinit var sendButton : ImageButton
    private lateinit var presenter : ChatPresenter
    private lateinit var chatappbar : AppBarLayout
    private lateinit var nick: String
    private lateinit var otherNick: String
    private lateinit var loader: LoadingDialog
    private lateinit var collapsingToolbar: SubtitleCollapsingToolbarLayout
    private lateinit var toolbar: Toolbar
    //maybe Ill move this someplace else later....

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chatpage)
        setupView()
        setupTitleColors()
        collapsingToolbar.title = otherNick
        loader = LoadingDialog(this)
        addTollbarListener()
        val options = FirebaseRecyclerOptions.Builder<MessageItem>()
            .setQuery(Firebase.database.getReference(ChatInteractor.MESSAGES+"/$nick/$otherNick"), MessageItem::class.java)
            .setLifecycleOwner(this)
            .build()
        chatrvAdapter = ChatItemsAdapter(options)
        chatrv.adapter = chatrvAdapter
        val linearLayout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        chatrv.layoutManager = linearLayout
        presenter = ChatPresenter(this)

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
        afterMessageDisplay()
    }

    private fun setupView() {
        chatrv = findViewById(R.id.chatRv)
        editMessage = findViewById(R.id.editMessage)
        sendButton = findViewById(R.id.sendButton)
        chatappbar = findViewById(R.id.chatappbar)
        toolbar = findViewById(R.id.toolbar)
        nick = intent.getStringExtra("nick").toString()
        otherNick = intent.getStringExtra("recipient").toString()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupTitleColors() {
        collapsingToolbar = findViewById(R.id.chatCollapsingToolbar)
        collapsingToolbar.setCollapsedTitleTextColor(getColor(R.color.white))
        collapsingToolbar.setExpandedSubtitleTextColor(getColor(R.color.white))
        collapsingToolbar.setCollapsedSubtitleTextColor(getColor(R.color.white))
        collapsingToolbar.setExpandedTitleTextColor(getColor(R.color.white))
    }

    private fun addTollbarListener() {
        toolbar.setNavigationOnClickListener {
            presenter.backClicked()
        }
    }

    fun showLoader(){
        loader.startLoadingDialog()
    }

    fun hideLoader(){
        loader.dismissDialog()
    }

    fun afterMessageDisplay(){
        chatrv.scrollToPosition(chatrvAdapter.itemCount - 1)
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

    override fun showHome() {
        finish()
    }
}