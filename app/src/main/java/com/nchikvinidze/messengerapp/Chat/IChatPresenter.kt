package com.nchikvinidze.messengerapp.Chat

import com.nchikvinidze.messengerapp.data.MessageItem
import java.net.URL

interface IChatPresenter{
    fun saveSentMessage(msg : MessageItem)
    fun backClicked()
    fun setupProffession(profesion: String)
    fun setupImage(url: URL)
    fun loadData(user: String)
}