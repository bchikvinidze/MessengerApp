package com.nchikvinidze.messengerapp.presenters.interfaces

import com.nchikvinidze.messengerapp.data.MessageItem

interface IChatPresenter{
    fun saveSentMessage(msg : MessageItem)
    fun showMessageHistory(nick : String, otherNick : String)
}