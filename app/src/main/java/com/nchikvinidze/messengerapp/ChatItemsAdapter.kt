package com.nchikvinidze.messengerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nchikvinidze.messengerapp.data.MessageItem

class ChatItemsAdapter() : RecyclerView.Adapter<ChatItemViewHolder>()  {

    var list = ArrayList<MessageItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ChatItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class ChatItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}