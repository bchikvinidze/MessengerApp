package com.nchikvinidze.messengerapp.Profile

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
import com.nchikvinidze.messengerapp.DependencyInjectorImpl
import com.nchikvinidze.messengerapp.LoadingDialog.LoadingDialog
import com.nchikvinidze.messengerapp.Login.LoginActivity
import com.nchikvinidze.messengerapp.R
import com.nchikvinidze.messengerapp.Signup.SignupActivity

class ProfileFragment(): Fragment(R.layout.profile), ProfileView.View {
    private lateinit var image : ImageView
    private lateinit var nickname : TextInputEditText
    private lateinit var whatido : TextInputEditText
    private lateinit var updateBtn : MaterialButton
    private lateinit var signOutButton : MaterialButton
    private lateinit var presenter: ProfileView.Presenter
    private var loader: LoadingDialog? = null
    var clickListener: SignOutClickListener? = null

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
        loader = activity?.let { LoadingDialog(it) }
        setPresenter(ProfilePresenter(this, DependencyInjectorImpl()))

        presenter.getProfileInfo()
        image.setOnClickListener {
            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, SignupActivity.imagePicker)
        }

        signOutButton.setOnClickListener {
            presenter.signOut()
        }

        updateBtn.setOnClickListener {
            presenter.updateUser(whatido.text.toString(), image.drawable)
        }
        return view
    }

    override fun setupProfileProfession(nick: String, profession : String){
        nickname.setText(nick)
        whatido.setText(profession)
    }

    override fun setupProfileImage(url : String){
        if(activity == null) {
            return
        }
        Glide.with(this).load(url).into(image)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == SignupActivity.imagePicker) {
            var imageUri = data?.data
            image.setImageURI(imageUri)
        }
    }

    override fun showLoader() {
        loader?.startLoadingDialog()
    }

    override fun hideLoader() {
        loader?.dismissDialog()
    }

    override fun showLogin() {
        clickListener?.signOutClicked()
    }

    override fun setPresenter(presenter: ProfileView.Presenter) {
        this.presenter = presenter
    }
}

interface SignOutClickListener {
    fun signOutClicked()
}