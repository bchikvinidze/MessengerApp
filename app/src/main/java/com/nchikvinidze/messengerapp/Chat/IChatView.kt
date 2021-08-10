package com.nchikvinidze.messengerapp.Chat

import com.nchikvinidze.messengerapp.data.MessageItem
import java.net.URL

interface IChatView {
    fun showHome()
    fun showProffession(name: String)
    fun showImage(url: URL)
}