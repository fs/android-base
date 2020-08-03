package com.flatstack.android.profile

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.flatstack.android.R
import com.flatstack.android.Router
import com.flatstack.android.util.observeBy
import com.flatstack.android.util.provideViewModel
import kotlinx.android.synthetic.main.activity_profile.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ProfileActivity : AppCompatActivity(), KodeinAware, OnRefreshListener {

    override val kodein: Kodein by kodein()

    private val viewModel: ProfileViewModel by provideViewModel()

    private val refreshLayout: SwipeRefreshLayout by lazy { swipe_layout }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        refreshLayout.setOnRefreshListener(this)

        viewModel.profileResponse.observeBy(
                this,
                onNext = {
                    showProfile()
                    showFirstName(it.firstName)
                    showLastName(it.lastName)
                },
                onError = ::showError,
                onLoading = ::visibleProgress)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_logout -> {
            logout()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onRefresh() {
        viewModel.updateProfile()
    }

    private fun showFirstName(firstName: String) {
        tv_first_name.text = firstName
    }

    private fun showLastName(lastName: String) {
        tv_last_name.text = lastName
    }

    private fun logout() {
        viewModel.logout()

        val router by kodein.instance<Router>()
        router.login(context = this, clearStack = true)
    }

    private fun visibleProgress(show: Boolean) {
        refreshLayout.isRefreshing = show
    }

    private fun showProfile() {
        cl_main.visibility = View.VISIBLE
    }

    private fun showError(errorText: String?) {
        errorText?.let {
            Toast.makeText(this, errorText, Toast.LENGTH_LONG).show()
        }
    }
}
