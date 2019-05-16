package com.flatstack.android.login

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.flatstack.android.R
import com.flatstack.android.Router
import com.flatstack.android.util.observeBy
import com.flatstack.android.util.provideViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), KodeinAware {

    private val viewModel: LoginViewModel by provideViewModel()

    override val kodein: Kodein by kodein()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel.loginResource.observeBy(
            this, onSuccess = {
                navigateToProfile()
            }, onError = ::showError,
            onLoading = ::setProgress
        )

        initListeners()
    }

    private fun navigateToProfile() {
        val router by kodein.instance<Router>()
        router.profile(context = this, clearStack = true)
    }

    private fun initListeners() {
        findViewById<Button>(R.id.bt_login).setOnClickListener {
            login()
        }
        findViewById<EditText>(R.id.et_password).apply {
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    login()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
        }
    }

    private fun login() {
        val username = findViewById<EditText>(R.id.et_login).text.toString()
        val password = findViewById<EditText>(R.id.et_password).text.toString()
        viewModel.login(username, password)
    }

    private fun setProgress(isLoading: Boolean) {
        if (isLoading) {
            findViewById<View>(R.id.pb_progress).visibility = View.VISIBLE
            findViewById<Button>(R.id.bt_login).isEnabled = false
        } else {
            findViewById<View>(R.id.pb_progress).visibility = View.GONE
            findViewById<Button>(R.id.bt_login).isEnabled = true
        }
    }

    private fun showError(errorText: String?) {
        errorText?.let {
            Toast.makeText(this, errorText, Toast.LENGTH_LONG).show()
        }
    }
}
