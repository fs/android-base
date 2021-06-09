package com.flatstack.android.registration

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.flatstack.android.R
import com.flatstack.android.Router
import com.flatstack.android.registration.entities.request.ImageUploadRequest
import com.flatstack.android.registration.entities.request.MetadataRequest
import com.flatstack.android.util.FileUtils
import com.flatstack.android.util.provideViewModel
import kotlinx.android.synthetic.main.activity_registration.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class RegistrationActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()

    private val viewModel: RegistrationViewModel by provideViewModel()
    private val fileUtils: FileUtils by instance()
    private val router: Router by instance()
    private var avatar: ImageUploadRequest? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        observeViewModel()
        initListeners()
    }

    private fun observeViewModel() {
        viewModel.errorData.observe(this) {
            showError(it)
        }

        viewModel.shouldShowProgressData.observe(this) {
            setProgress(it)
        }

        viewModel.navigateToProfileData.observe(this) { navigateToProfile() }
    }

    private fun initListeners() {
        bt_sign_up.setOnClickListener { register() }
        bt_select_avatar.setOnClickListener { pickImage() }
        et_confirm_password.apply {
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    register()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
        }
    }

    private fun navigateToProfile() { router.profile(this, shouldClearStack = true) }

    private fun register() {
        val firstName = et_first_name.text.toString()
        val lastName = et_last_name.text.toString()
        val email = et_email.text.toString()
        val password = et_password.text.toString()
        val confirmPassword = et_confirm_password.text.toString()
        viewModel.register(
            avatar = avatar,
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password,
            confirmPassword = confirmPassword
        )
    }

    private fun pickImage() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = MEDIA_TYPE
        startActivityForResult(intent, PICK_IMAGE_FOR_AVATAR)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            data?.let { intent ->
                intent.data?.let { uri ->

                    val fileName = fileUtils.getRealFileName(uri)

                    avatar = ImageUploadRequest(
                        id = "",
                        metadata = MetadataRequest(
                            size = fileUtils.getFileSizeInBytes(uri),
                            fileName = fileName,
                            mimeType = fileUtils.getFileMimeType(uri)
                        )
                    )
                    avatar?.file = fileUtils.getFileFromContentUri(uri, fileName)

                    Glide.with(this)
                        .load(uri)
                        .transform(CenterCrop(), RoundedCorners(PREVIEW_PICTURE_CORNER_RADIUS))
                        .into(iv_avatar_preview)
                    tv_avatar_filename.text = fileName
                }
            }
        }
    }

    private fun setProgress(isLoading: Boolean) {
        pb_progress.isVisible = isLoading
    }

    private fun showError(errorText: String?) {
        errorText?.let {
            Toast.makeText(this, errorText, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        const val PICK_IMAGE_FOR_AVATAR = 1
        const val PREVIEW_PICTURE_CORNER_RADIUS = 10
        const val MEDIA_TYPE = "image/*"
    }
}
