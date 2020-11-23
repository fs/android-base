package com.flatstack.android.signup

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.flatstack.android.R
import com.flatstack.android.Router
import com.flatstack.android.util.observeBy
import com.flatstack.android.util.provideViewModel
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.pb_progress
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.io.File

class SignUpActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()

    private val viewModel: SignUpViewModel by provideViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        viewModel.signUpResource.observeBy(
            this,
            onSuccess = ::navigateToProfile,
            onLoading = ::setProgress,
            onError = ::showError
        )

        initListeners()
    }

    private fun navigateToProfile() {
        val router by kodein.instance<Router>()
        router.profile(context = this, clearStack = true)
    }

    private fun initListeners() {
        bt_sign_up.setOnClickListener { signUp() }
        bt_choose_avatar.setOnClickListener { chooseImageFromInternalStorage() }
    }

    private fun chooseImageFromInternalStorage() {
        startActivityForResult(
            Intent(Intent.ACTION_GET_CONTENT).apply { type = "image/*" },
            PICK_PHOTO_FOR_AVATAR
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == RESULT_OK) {
            data?.let {
                it.data?.let {
                    val inputStream = this.contentResolver.openInputStream(it)
                    iv_default_avatar.setImageDrawable(Drawable.createFromStream(inputStream, "is"))
                }
                tv_filename.text = File(it.dataString).name
            }
        }
    }

    private fun signUp() {
        viewModel.signUp(
            email = et_email.text.toString(),
            firstName = et_first_name.text.toString(),
            lastName = et_last_name.text.toString(),
            password = et_password.text.toString()
        )
    }

    private fun setProgress(isLoading: Boolean) {
        pb_progress.isVisible = isLoading
        bt_sign_up.isEnabled = !isLoading
    }

    private fun showError(errorText: String?) {
        errorText?.let {
            Toast.makeText(this, errorText, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        private const val PICK_PHOTO_FOR_AVATAR = 1
    }
}