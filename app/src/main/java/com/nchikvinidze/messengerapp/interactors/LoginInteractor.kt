package com.nchikvinidze.messengerapp.interactors

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.nchikvinidze.messengerapp.presenters.ILoginPresenter

class LoginInteractor(val presenter: ILoginPresenter)  {
    val database = Firebase.database
    val storage = Firebase.storage
    val auth = Firebase.auth


}