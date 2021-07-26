package com.nchikvinidze.messengerapp.Signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.Login.LoginActivity

class SignupActivity : AppCompatActivity(), ISignupView {

    companion object{
        val imagePicker = 100
    }

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
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, imagePicker)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == imagePicker) {
            var imageUri = data?.data
            img.setImageURI(imageUri)
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