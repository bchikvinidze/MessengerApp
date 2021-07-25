package com.nchikvinidze.messengerapp.presenters.interfaces

interface IChatPresenter{
    fun setCurrentNickname(nick : String)
    fun saveSentMessage()
}