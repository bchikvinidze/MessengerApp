package com.nchikvinidze.messengerapp.Profile

import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.nchikvinidze.messengerapp.Chat.ChatInteractor
import com.nchikvinidze.messengerapp.Login.LoginInteractor
import com.nchikvinidze.messengerapp.Signup.ISignupPresenter
import com.nchikvinidze.messengerapp.data.User
import java.io.ByteArrayOutputStream

class ProfileInteractor(val presenter: ProfilePresenter) {
    val database = Firebase.database
    val storage = Firebase.storage
    val auth = Firebase.auth
    //var sharedPreferences = sharedPref

    fun updateUser(nick : String, newProf : String, newImage : Drawable){
        if(auth.currentUser == null) auth.signInAnonymously()
        val usersRef = database.getReference("users")

        usersRef.child(nick).get().addOnSuccessListener {
            if(it.exists()){
                val imgRef = storage.getReference(nick)
                val bitmap = (newImage as BitmapDrawable).bitmap
                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val data = baos.toByteArray()
                imgRef.putBytes(data)
                usersRef.push().key?.let {
                    usersRef.child(nick).child("prof").setValue(newProf)
                }
            } else {
                Log.e("USERS UPDATE ERROR", "NOT FOUND")
            }
        }
    }

    fun getProfileInfo(nick : String){
        val usersRef = database.getReference("users")
        usersRef.child(nick).get().addOnSuccessListener {
            if(it.exists()){
                var profession = it.child("prof").getValue<String>().toString()
                presenter.setupProfileProfession(profession)
            }
        }

        val imageref = storage.reference.child(nick)
        imageref.downloadUrl.addOnSuccessListener {
            var url = it
            presenter.setupProfileImage(url.toString())
        }
    }

    fun signOut(){
        auth.signOut()
    }
}