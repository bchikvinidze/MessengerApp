package com.nchikvinidze.messengerapp.Search

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.data.User

class UserItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val nameTextView = itemView.findViewById<TextView>(R.id.name)
    private val profTextView = itemView.findViewById<TextView>(R.id.proffession)
    var clickListener: ClickListener? = null
    private var user: User? = null

    init {
        itemView.setOnClickListener {
            user?.let { it1 -> clickListener?.onClicked(it1) }
        }
    }

    fun fill(item: User) {
        user = item
        nameTextView.text = item.nick
        profTextView.text = item.prof
    }
}