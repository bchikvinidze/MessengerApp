package com.nchikvinidze.messengerapp

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.ktx.storageMetadata
import com.nchikvinidze.messengerapp.data.User
import com.nchikvinidze.messengerapp.presenters.IMainPresenter
import java.io.ByteArrayOutputStream

class MainInteractor(val presenter: IMainPresenter) {
    val database = Firebase.database
    val storage = Firebase.storage
    val auth = Firebase.auth

    fun newUserToDb(nick : String, psw : String, prof: String, img : Drawable){
        if(auth.currentUser == null){
            auth.signInAnonymously()
        }
        //user details upload
        val usersRef = database.getReference("users")
        usersRef.push().key?.let {
            usersRef.child(it).setValue(User(nick,psw,prof))
        }
        //image upload to storage
        var metadata = storageMetadata {
            contentType = ""
        }
        val imgRef = storage.getReference(nick)
        val bitmap = (img as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        imgRef.putBytes(data)
    }
}