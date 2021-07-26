package com.nchikvinidze.messengerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.nchikvinidze.messengerapp.data.MessageItem

class ChatItemsAdapter(options : FirebaseRecyclerOptions<MessageItem>) : FirebaseRecyclerAdapter<MessageItem, ChatItemViewHolder>(options) {
    val VIEW_TYPE_SENT = 1
    val VIEW_TYPE_RECEIVED = 2
    //var list = ArrayList<MessageItem>()

    override fun getItemViewType(position: Int): Int {
        /*var msg = list[position]
        if(msg.sent) return VIEW_TYPE_SENT
        return VIEW_TYPE_RECEIVED*/
        if(getItem(position).sent) return VIEW_TYPE_SENT
        return VIEW_TYPE_RECEIVED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemViewHolder {
        if(viewType == VIEW_TYPE_RECEIVED) {
            var view =
                LayoutInflater.from(parent.context).inflate(R.layout.recieved_message, parent, false)
            return ChatItemViewHolder(view)
        }else {
            var view =
                LayoutInflater.from(parent.context).inflate(R.layout.sent_message, parent, false)
            return ChatItemViewHolder(view)
        }
    }

    /*override fun onBindViewHolder(holder: ChatItemViewHolder, position: Int) {
        var msg = list[position]
        holder.bind(msg)
    }*/

    override fun getItemCount(): Int {
        return super.getItemCount()
    }

    override fun onBindViewHolder(holder: ChatItemViewHolder, position: Int, model: MessageItem) {
        //var msg = list[position]
        holder.bind(model)
    }

    override fun getItem(position: Int): MessageItem {
        return super.getItem(position)
    }

}

class ChatItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var msgText = itemView.findViewById<TextView>(R.id.messagetext)
    var time = itemView.findViewById<TextView>(R.id.message_timestamp)

    fun bind(msg : MessageItem){
        msgText.text = msg.text
        time.text = msg.time
    }
}