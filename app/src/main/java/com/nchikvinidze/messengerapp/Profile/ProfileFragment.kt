package com.nchikvinidze.messengerapp.Profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.nchikvinidze.messengerapp.Login.LoginActivity
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.Signup.SignupActivity

class ProfileFragment(): Fragment(R.layout.profile) {
    companion object {
        @JvmStatic
        fun newInstance(nicknameText: String) = ProfileFragment().apply {
            arguments = Bundle().apply {
                putString("nick", nicknameText)
            }
        }
    }

    lateinit var image : ImageView
    lateinit var nickname : TextInputEditText
    lateinit var whatido : TextInputEditText
    lateinit var updateBtn : MaterialButton
    lateinit var signOutButton : MaterialButton
    lateinit var presenter: ProfilePresenter
    lateinit var nicknameText : String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.profile, container, false)
        image = view.findViewById(R.id.defaultImage)
        nickname = view.findViewById(R.id.nickname)
        whatido = view.findViewById(R.id.whatido)
        updateBtn = view.findViewById(R.id.updateButton)
        signOutButton = view.findViewById(R.id.signoutButton)
        presenter = ProfilePresenter(this)
        nickname.setText(nicknameText)
        presenter.getProfileInfo(nicknameText)

        image.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, SignupActivity.imagePicker)
        }

        signOutButton.setOnClickListener {
            presenter.signOut()
            var intent = Intent(view.context, LoginActivity::class.java)
            intent.putExtra("status", "logout")
            startActivity(intent)
        }

        updateBtn.setOnClickListener {
            presenter.updateUser(nicknameText, whatido.text.toString(), image.drawable)
        }
        return view
    }

    fun setupProfileProfession(profession : String){
        whatido.setText(profession)
    }

    fun setupProfileImage(url : String){

        Glide.with(this).load(url).into(image)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == SignupActivity.imagePicker) {
            var imageUri = data?.data
            image.setImageURI(imageUri)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.getString("nick")?.let {
            nicknameText = it
        }
    }

}