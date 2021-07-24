package com.nchikvinidze.messengerapp.Views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nchikvinidze.messengerapp.ChatItemsAdapter
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.data.MessageItem

class ChatActivity : AppCompatActivity(){

    lateinit var chatrv : RecyclerView
    lateinit var chatrvAdapter : ChatItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chatpage)
        chatrv = findViewById(R.id.chatRv)
        chatrvAdapter = ChatItemsAdapter()
        chatrv.adapter = chatrvAdapter
        chatrv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false)

        //UIs satesto mesijebi
        var m1 = MessageItem("22:10", true, "bubu", "vaime ra gveshveleba")
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
    }
}