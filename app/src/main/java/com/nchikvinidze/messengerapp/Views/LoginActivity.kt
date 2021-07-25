package com.nchikvinidze.messengerapp.Views

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.nchikvinidze.messengerapp.presenters.LoginPresenter
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.Views.Interfaces.ILoginView

class LoginActivity : AppCompatActivity(), ILoginView {

    //views to be initialized later
    lateinit var nicknameField : TextInputEditText
    lateinit var passwordField : TextInputEditText
    lateinit var img: ImageView
    lateinit var presenter: LoginPresenter
    lateinit var signinButton : MaterialButton
    lateinit var signupButton : MaterialButton
    lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authorization)

        //initialize views
        nicknameField = findViewById(R.id.nickname)
        passwordField = findViewById(R.id.password)
        img = findViewById(R.id.defaultImage)
        signinButton = findViewById(R.id.signinButton)
        signupButton = findViewById(R.id.signupButton)
        sharedPref = getPreferences(Context.MODE_PRIVATE) ?: return
        presenter = LoginPresenter(this, sharedPref)
        //if already logged on, move to home:
        presenter.checkAlreadyLoggedIn()

        //set listeners
        signinButton.setOnClickListener {
            presenter.signInAttempt(nicknameField.text.toString(), passwordField.text.toString())
        }
        signupButton.setOnClickListener {
            var intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

    }

    override fun notifyIncorrectCredentials() {
        Toast.makeText(this, "Incorrect credentials. Try again.", Toast.LENGTH_SHORT).show()
    }

    override fun moveToHome(nick : String) {
        //TODO axali intent home-ze gadasasvlelad.
        Toast.makeText(this, "aq intenti chavamatot", Toast.LENGTH_SHORT).show()
        //droebit chatze gadasvlas vizav
        var intent = Intent(this, ChatActivity::class.java)
        intent.putExtra("nickname", nick)
        intent.putExtra("recipient", "babuca")
        startActivity(intent)
    }
}