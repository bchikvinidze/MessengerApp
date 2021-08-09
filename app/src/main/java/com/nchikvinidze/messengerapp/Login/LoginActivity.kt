package com.nchikvinidze.messengerapp.Login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.nchikvinidze.messengerapp.LoadingDialog.LoadingDialog
import com.nchikvinidze.messengerapp.Navigation.NavigationActivity
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.Signup.SignupActivity

class LoginActivity : AppCompatActivity(), ILoginView {

    //views to be initialized later
    private lateinit var nicknameField : TextInputEditText
    private lateinit var passwordField : TextInputEditText
    private lateinit var img: ImageView
    private lateinit var presenter: LoginPresenter
    private lateinit var signinButton : MaterialButton
    private lateinit var signupButton : MaterialButton
    private lateinit var sharedPref : SharedPreferences
    private lateinit var loader: LoadingDialog

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
        loader = LoadingDialog(this)
        presenter = LoginPresenter(this, sharedPref)
        //if already logged on, move to home:
        if(intent.getStringExtra("status") != "logout")
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
        var intent = Intent(this, NavigationActivity::class.java)
        intent.putExtra("nick", nick)
        startActivity(intent)
    }

    override fun showLoader() {
        loader.startLoadingDialog()
    }

    override fun hideLoader() {
        loader.dismissDialog()
    }
}