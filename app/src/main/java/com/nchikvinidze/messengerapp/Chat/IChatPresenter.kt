package com.nchikvinidze.messengerapp.Chat

import com.nchikvinidze.messengerapp.data.MessageItem

interface IChatPresenter{
    fun saveSentMessage(msg : MessageItem)
    fun backClicked()
}