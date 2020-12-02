package com.flatstack.android.signup

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.flatstack.android.R
import com.flatstack.android.Router
import com.flatstack.android.type.ImageUploader
import com.flatstack.android.type.ImageUploaderMetadata
import com.flatstack.android.util.observeBy
import com.flatstack.android.util.provideViewModel
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream


class SignUpActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()

    private val viewModel: SignUpViewModel by provideViewModel()
    private var imageUploader: ImageUploader? = null
    private var file: File? = null

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
            Intent(Intent.ACTION_GET_CONTENT).apply { type = "image/jpeg" },
            PICK_PHOTO_FOR_AVATAR
        )
    }

    @SuppressLint("NewApi")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == RESULT_OK) {
            data?.let {
                it.data?.let { uri ->
                    val inputStream = this.contentResolver.openInputStream(uri)
                    iv_default_avatar.setImageDrawable(Drawable.createFromStream(inputStream, "is"))
                    getDriveFilePath(uri)?.let { path ->
                        File(path).let { file ->
                            this.file = file
                            tv_filename.text = file.name
                            imageUploader = ImageUploader(
                                id = file.name,
                                metadata = ImageUploaderMetadata(
                                    filename = file.name,
                                    mimeType = "image/jpeg",
                                    size = file.length().toInt()
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("Recycle")
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    private fun getDriveFilePath(
        uri: Uri
    ): String? {
        val returnCursor =
            contentResolver.query(uri, null, null, null, null)
        val nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        val sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE)
        returnCursor.moveToFirst()
        val name = returnCursor.getString(nameIndex)
        val size = java.lang.Long.toString(returnCursor.getLong(sizeIndex))
        val file = File(cacheDir, name)
        try {
            val inputStream: InputStream = contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(file)
            var read = 0
            val maxBufferSize = 1 * 1024 * 1024
            val bytesAvailable: Int = inputStream.available()

            //int bufferSize = 1024;
            val bufferSize = Math.min(bytesAvailable, maxBufferSize)
            val buffers = ByteArray(bufferSize)
            while (inputStream.read(buffers).also({ read = it }) != -1) {
                outputStream.write(buffers, 0, read)
            }
            Log.e("File Size", "Size " + file.length())
            inputStream.close()
            outputStream.close()
            Log.e("File Path", "Path " + file.path)
            Log.e("File Size", "Size " + file.length())
        } catch (e: Exception) {
            Log.e("Exception", e.message)
        }
        return file.path
    }


    private fun signUp() {
        viewModel.signUp(
            email = et_email.text.toString(),
            firstName = et_first_name.text.toString(),
            lastName = et_last_name.text.toString(),
            password = et_password.text.toString(),
            avatar = imageUploader,
            file = file
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