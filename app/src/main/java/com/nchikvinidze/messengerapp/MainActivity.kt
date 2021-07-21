package com.nchikvinidze.messengerapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity(), IMainView {

    //views to be inizialized later
    lateinit var nicknameField : TextInputEditText
    lateinit var passwordField : TextInputEditText
    lateinit var signInButton : MaterialButton
    lateinit var signUpButton : MaterialButton
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authorization)
        //val database = Firebase.database

        //initialize views
        nicknameField = findViewById(R.id.nickname)
        passwordField = findViewById(R.id.password)
        signInButton = findViewById(R.id.signinButton)
        signUpButton = findViewById(R.id.signupButton)
        presenter = MainPresenter(this)

        //set listeners
        signInButton.setOnClickListener {

        }

        signUpButton.setOnClickListener {

        }
    }
}