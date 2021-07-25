package com.nchikvinidze.messengerapp.Views.Interfaces

import com.nchikvinidze.messengerapp.data.MessageItem

interface IChatView {
    fun displayMessage(msg : MessageItem)
}