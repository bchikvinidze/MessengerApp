package com.nchikvinidze.messengerapp.Views

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nchikvinidze.messengerapp.ChatItemsAdapter
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.Views.Interfaces.IChatView
import com.nchikvinidze.messengerapp.data.MessageItem
import com.nchikvinidze.messengerapp.presenters.ChatPresenter
import com.nchikvinidze.messengerapp.presenters.LoginPresenter

class ChatActivity : AppCompatActivity(), IChatView{

    lateinit var chatrv : RecyclerView
    lateinit var chatrvAdapter : ChatItemsAdapter
    lateinit var editMessage : EditText
    lateinit var sendButton : ImageButton
    lateinit var sharedPref : SharedPreferences
    lateinit var presenter : ChatPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chatpage)
        chatrv = findViewById(R.id.chatRv)
        editMessage = findViewById(R.id.editMessage)
        sendButton = findViewById(R.id.sendButton)
        sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        chatrvAdapter = ChatItemsAdapter()
        chatrv.adapter = chatrvAdapter
        chatrv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)
        presenter = ChatPresenter(this, sharedPref)

        sendButton.setOnClickListener {
            //save to firebase
            //TODO
            //display
            //chatrvAdapter.list.add(MessageItem(time, true, nick, editMessage.text))
            chatrvAdapter.notifyDataSetChanged()
        }

        //UIs satesto mesijebi
        /*var m1 = MessageItem("22:10", true, "bubu", "vaime ra gveshveleba")
        var m2 = MessageItem("22:10", true, "bubu", "vaime ra gveshvelebaa")
        var m3 = MessageItem("22:10", false, "bubu", "vaime ra gveshvelebaaa")
        var m4 = MessageItem("22:10", true, "bubu", "vaime ra gveshvelebaaaa jfdsidf ujofijsodijf djfosijdoifj fiodjofijsods dijifjdijf fdjfidddddddddddddddddddddd")

        chatrvAdapter.list.add(m3)
        chatrvAdapter.notifyDataSetChanged()
        chatrvAdapter.list.add(m2)
        chatrvAdapter.notifyDataSetChanged()
        chatrvAdapter.list.add(m3)
        chatrvAdapter.notifyDataSetChanged()
        chatrvAdapter.list.add(m4)
        chatrvAdapter.notifyDataSetChanged()
        chatrvAdapter.list.add(m4)
        chatrvAdapter.notifyDataSetChanged()
        chatrvAdapter.list.add(m4)
        chatrvAdapter.notifyDataSetChanged()
        chatrvAdapter.list.add(m4)
        chatrvAdapter.notifyDataSetChanged()
        chatrvAdapter.list.add(m4)
        chatrvAdapter.notifyDataSetChanged()
        chatrvAdapter.list.add(m4)
        chatrvAdapter.notifyDataSetChanged()*/
    }
}