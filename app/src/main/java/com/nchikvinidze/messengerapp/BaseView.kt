package com.nchikvinidze.messengerapp

interface BaseView<T> {
    fun setPresenter(presenter : T)
}