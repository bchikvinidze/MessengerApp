package com.nchikvinidze.messengerapp.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.nchikvinidze.messengerapp.presenters.LoginPresenter
import com.nchikvinidze.messengerapp.R

class SignupActivity : AppCompatActivity(), ILoginView {

    //views to be inizialized later
    lateinit var nicknameField : TextInputEditText
    lateinit var passwordField : TextInputEditText
    lateinit var whatIdoField: TextInputEditText
    lateinit var img: ImageView
    //lateinit var signInButton : MaterialButton
    lateinit var signUpButton : MaterialButton
    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)
        //val database = Firebase.database

        //initialize views
        nicknameField = findViewById(R.id.nickname)
        passwordField = findViewById(R.id.password)
        whatIdoField = findViewById(R.id.profession)
        img = findViewById(R.id.defaultImage)
        //signInButton = findViewById(R.id.signinButton)
        signUpButton = findViewById(R.id.signupButton)
        presenter = LoginPresenter(this)
        //set listeners
        //signInButton.setOnClickListener {

        //}


        signUpButton.setOnClickListener { // aq todo: ukve arsebuli nicknames daregistrirebaze toast gamogdeba
            var nick = nicknameField.text.toString()
            if(!nick.contains("\n")) { // nicknamebshi newline modi ar gvinda
                var psw = passwordField.text.toString() //amas schirdeba rom rgolebi gamochndes asoebis nacvlad.
                var prof = whatIdoField.text.toString()
                var image = img.drawable
                presenter.saveNewUser(nick, psw, prof, image)
            }
        }
    }
}