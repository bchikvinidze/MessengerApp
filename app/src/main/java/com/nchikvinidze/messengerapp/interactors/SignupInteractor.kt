package com.nchikvinidze.messengerapp.interactors

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.ktx.storageMetadata
import com.nchikvinidze.messengerapp.data.User
import com.nchikvinidze.messengerapp.presenters.ILoginPresenter
import com.nchikvinidze.messengerapp.presenters.ISignupPresenter
import java.io.ByteArrayOutputStream

class SignupInteractor(val presenter: ISignupPresenter) {
    val database = Firebase.database
    val storage = Firebase.storage
    val auth = Firebase.auth

    fun newUserToDb(nick : String, psw : String, prof: String, img : Drawable){
        if(auth.currentUser == null){
            auth.signInAnonymously()
        }
        val usersRef = database.getReference("users")

        //check if user exists
        userExists(nick, psw, prof, img, usersRef)
    }

    fun userExists(nick : String, psw : String, prof: String, img : Drawable, usersRef : DatabaseReference){
        usersRef.child("users").child(nick).get().addOnSuccessListener {
            if(it.exists()){
                presenter.notifyUserExists()
            } else {
                insertNewUser(nick, psw, prof, img, usersRef)
            }
        }
    }

    fun insertNewUser(nick : String, psw : String, prof: String, img : Drawable, usersRef : DatabaseReference){
        //user details upload
        usersRef.push().key?.let {
            usersRef.child(nick).setValue(User(nick,psw,prof))
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
        //move to sign in page
        presenter.moveToSignin()
    }

}