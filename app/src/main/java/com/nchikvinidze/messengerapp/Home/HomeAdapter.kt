package com.nchikvinidze.messengerapp.Home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.data.MessageItem

class HomeAdapter(var model: List<MessageItem>, var context: Context) : RecyclerView.Adapter<MessageItemViewHolder>() {
    var clickListener: MessageClickListener? = null

    override fun getItemCount(): Int {
        return model.size
    }

    override fun onBindViewHolder(holder: MessageItemViewHolder, position: Int) {
        val item = model[position]
        holder.clickListener = clickListener
        holder.fill(item)
//        Glide.with(context)
//            .load(item.url)
//            .into(holder.iconImageView);
    }

    fun update(model: List<MessageItem>) {
        this.model = model
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageItemViewHolder {
        return MessageItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.chat_item, parent, false))
    }

}

interface MessageClickListener {
    fun onClicked(user: String)
}