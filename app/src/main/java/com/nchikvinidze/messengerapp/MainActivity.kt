package com.nchikvinidze.messengerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.nchikvinidze.messengerapp.Home.HomeActivity
import java.io.Serializable

class MainActivity : AppCompatActivity(), IMainView {

    //views to be inizialized later
    lateinit var nicknameField : TextInputEditText
    lateinit var passwordField : TextInputEditText
    lateinit var whatIdoField: TextInputEditText
    lateinit var img: ImageView
    //lateinit var signInButton : MaterialButton
    lateinit var signUpButton : MaterialButton
    lateinit var presenter: MainPresenter

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
        presenter = MainPresenter(this)
        //set listeners
        //signInButton.setOnClickListener {

        //}


        signUpButton.setOnClickListener {
            var nick = nicknameField.text.toString()
            var psw = passwordField.text.toString() //amas schirdeba rom rgolebi gamochndes asoebis nacvlad.
            var prof = whatIdoField.text.toString()
            var image = img.drawable
            presenter.saveNewUser(nick, psw, prof, image)
            var addIntent = Intent(this, HomeActivity::class.java)
            startActivity(addIntent)
        }
    }
}