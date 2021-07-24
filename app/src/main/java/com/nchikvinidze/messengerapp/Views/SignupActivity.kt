package com.nchikvinidze.messengerapp.Views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.nchikvinidze.messengerapp.presenters.LoginPresenter
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.presenters.SignupPresenter

class SignupActivity : AppCompatActivity(), ISignupView {

    //views to be inizialized later
    lateinit var nicknameField : TextInputEditText
    lateinit var passwordField : TextInputEditText
    lateinit var whatIdoField: TextInputEditText
    lateinit var img: ImageView
    lateinit var signUpButton : MaterialButton
    lateinit var presenter: SignupPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)

        //initialize views
        nicknameField = findViewById(R.id.nickname)
        passwordField = findViewById(R.id.password)
        whatIdoField = findViewById(R.id.profession)
        img = findViewById(R.id.defaultImage)
        signUpButton = findViewById(R.id.signupButton)
        presenter = SignupPresenter(this)

        img.setOnClickListener {

        }

        signUpButton.setOnClickListener {
            var nick = nicknameField.text.toString()
            if(!nick.contains("\n")) { // nicknamebshi newline modi ar gvinda
                var psw = passwordField.text.toString() //amas schirdeba rom rgolebi gamochndes asoebis nacvlad.
                var prof = whatIdoField.text.toString()
                var image = img.drawable
                presenter.saveNewUser(nick, psw, prof, image)
            }
        }
    }

    override fun userExists(){
        Toast.makeText(this, "Enter another username", Toast.LENGTH_SHORT).show()
    }

    override fun moveToSignIn() {
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}