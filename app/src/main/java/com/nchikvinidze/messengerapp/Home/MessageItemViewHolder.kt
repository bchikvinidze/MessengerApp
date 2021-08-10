package com.nchikvinidze.messengerapp.Home

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.Search.ClickListener
import com.nchikvinidze.messengerapp.data.MessageItem
import com.nchikvinidze.messengerapp.prefs

class MessageItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val nameTextView = itemView.findViewById<TextView>(R.id.chatItemName)
    private val messageTextView = itemView.findViewById<TextView>(R.id.chatItemMessage)
    var iconImageView = itemView.findViewById<ImageView>(R.id.chatUserIcon)
    var clickListener: MessageClickListener? = null
    private var message: MessageItem? = null
    private var user: String? = null

    init {
        itemView.setOnClickListener {
            user?.let { it1 -> clickListener?.onClicked(it1) }
        }
    }

    fun fill(item: MessageItem) {
        message = item
        if (item.from == prefs.userName)
            user = item.to
        else
            user = item.from
        nameTextView.text = user
        messageTextView.text = item.text
    }
}