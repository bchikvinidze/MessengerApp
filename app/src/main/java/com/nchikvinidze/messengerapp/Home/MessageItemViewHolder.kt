package com.nchikvinidze.messengerapp.Home

import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.data.MessageItem
import com.nchikvinidze.messengerapp.prefs
import java.text.SimpleDateFormat

class MessageItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val nameTextView = itemView.findViewById<TextView>(R.id.chatItemName)
    private val timeTextView = itemView.findViewById<TextView>(R.id.chat_time)
    private val messageTextView = itemView.findViewById<TextView>(R.id.chatItemMessage)
    var iconImageView = itemView.findViewById<ImageView>(R.id.chatUserIcon)
    var clickListener: MessageClickListener? = null
    private var message: MessageItem? = null
    private var user: String? = null
    val simpleDateFormat = SimpleDateFormat("dd MMM")
    val minutesDateFormat = SimpleDateFormat("m")
    val hourDateFormat = SimpleDateFormat("h")
    val minutes = 3600000
    val hours = 86400000
    val seconds = 600000

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
        val now = System.currentTimeMillis()
        val difference = now - item.timemillis
        if (difference/seconds < 1) {
            timeTextView.text = "1" + " " + itemView.context.getString(R.string.minute)
        } else if (difference/minutes < 1) {
            timeTextView.text = minutesDateFormat.format(difference) + " " + itemView.context.getString(R.string.minute)
        } else if (difference/hours < 1) {
            timeTextView.text = hourDateFormat.format(difference) + " " + itemView.context.getString(R.string.hour)
        } else {
            timeTextView.text = simpleDateFormat.format(item.timemillis)
        }
    }
}