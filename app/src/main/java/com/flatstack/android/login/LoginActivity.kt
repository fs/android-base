package com.flatstack.android.login

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.flatstack.android.R
import com.flatstack.android.Router
import com.flatstack.android.util.observeBy
import com.flatstack.android.util.provideViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()

    private val viewModel: LoginViewModel by provideViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel.loginResource.observeBy(
                this,
                onSuccess = ::navigateToProfile,
                onError = ::showError,
                onLoading = ::setProgress
        )

        initListeners()
    }

    private fun navigateToProfile() {
        val router by kodein.instance<Router>()
        router.profile(context = this, clearStack = true)
    }

    private fun initListeners() {
        bt_login.setOnClickListener { login() }
        et_password.apply {
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
        val username = et_login.text.toString()
        val password = et_password.text.toString()
        viewModel.login(username, password)
    }

    private fun setProgress(isLoading: Boolean) {
        if (isLoading) {
            pb_progress.visibility = View.VISIBLE
            bt_login.isEnabled = false
        } else {
            pb_progress.visibility = View.GONE
            bt_login.isEnabled = true
        }
    }

    private fun showError(errorText: String?) {
        errorText?.let {
            Toast.makeText(this, errorText, Toast.LENGTH_LONG).show()
        }
    }
}
