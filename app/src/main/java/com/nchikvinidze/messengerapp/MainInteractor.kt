package com.nchikvinidze.messengerapp

import android.graphics.drawable.Drawable
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainInteractor(val presenter: IMainPresenter) {
    val database = Firebase.database

    fun newUserToDb(nick : String, psw : String, prof: String, img : Drawable){
        val myRef = database.getReference("message")
        myRef.setValue("Hi hellouuuuu")
    }
}