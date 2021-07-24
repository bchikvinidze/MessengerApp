package com.nchikvinidze.messengerapp.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.nchikvinidze.messengerapp.presenters.MainPresenter
import com.nchikvinidze.messengerapp.R

class LoginActivity : AppCompatActivity(), IMainView {

    //views to be initialized later
    lateinit var nicknameField : TextInputEditText
    lateinit var passwordField : TextInputEditText
    lateinit var img: ImageView
    lateinit var presenter: MainPresenter
    lateinit var signinButton : MaterialButton
    lateinit var signupButton : MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authorization)

        //initialize views
        nicknameField = findViewById(R.id.nickname)
        passwordField = findViewById(R.id.password)
        img = findViewById(R.id.defaultImage)
        signinButton = findViewById(R.id.signinButton)
        signupButton = findViewById(R.id.signupButton)
        presenter = MainPresenter(this)
        //set listeners
        signinButton.setOnClickListener {

        }
        signupButton.setOnClickListener {
            var intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}