package com.nchikvinidze.messengerapp.Search

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.nchikvinidze.messengerapp.data.User
import com.nchikvinidze.messengerapp.prefs
import java.net.URL

class SearchInteractor(val presenter: SearchList.Presenter) {
    val database = Firebase.database
    val storage = Firebase.storage
    val auth = Firebase.auth

    fun loadUsers(name: String?){
        if(auth.currentUser == null) auth.signInAnonymously()
        val usersRef = database.getReference("users")

        usersRef.get().addOnSuccessListener {
            var users: ArrayList<User> = ArrayList()
            it.children.forEach{
                it.getValue(User::class.java)?.let {
                    if (it.nick?.contains(name ?: "") == true && it.nick != prefs.userName) {
                        val imgRef = storage.getReference(it.nick ?: "")
                        val user = it
                        imgRef.downloadUrl.addOnSuccessListener {
                            user.url = URL(it.toString())
                            users.add(user)
                            presenter.dataLoaded(users)
                        }
                    }
                }
            }
            if (users.size == 0) {
                presenter.dataLoaded(users)
            }
        }
    }

}