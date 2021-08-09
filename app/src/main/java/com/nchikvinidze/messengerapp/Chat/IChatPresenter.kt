package com.nchikvinidze.messengerapp.Chat

import com.nchikvinidze.messengerapp.data.MessageItem

interface IChatPresenter{
    fun saveSentMessage(msg : MessageItem)
    fun showMessageHistory(nick : String, otherNick : String)
    fun displayDownloadedMessage(msg : MessageItem)
    fun displayDownloadedMessageList(lst : List<MessageItem>)
    fun backClicked()
}