package com.flatstack.android.profile

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.flatstack.android.R
import com.flatstack.android.R.layout
import com.flatstack.android.R.menu
import com.flatstack.android.R.string
import com.flatstack.android.Router
import com.flatstack.android.mainscreen.TestDialog
import com.flatstack.android.profile.entities.Book
import com.flatstack.android.util.observeBy
import com.flatstack.android.util.provideViewModel
import com.flatstack.android.util.ui.BaseActivity
import com.flatstack.android.util.ui.ScreenConfig
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ProfileActivity : BaseActivity(), KodeinAware, OnRefreshListener {

    override val screenConfig: ScreenConfig
        get() = ScreenConfig(
            layout.activity_profile, titleRes = string.app_name, enableBackButton = true,
            menuRes = menu.menu_profile
        )

    private val viewModel: ProfileViewModel by provideViewModel()

    private val refreshLayout: SwipeRefreshLayout by lazy {
        findViewById<SwipeRefreshLayout>(R.id.swipe_layout)
    }

    override val kodein: Kodein by kodein()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        refreshLayout.setOnRefreshListener(this)
        onRefresh()

        viewModel.profileResponse.observeBy(
            this, onNext = {
            showProfile()
            showUsername(it.username)
            showFavoriteBook(it.favoriteBook)
            showBooks(it.booksRead)
        }, onError = ::showError, onLoading = ::visibleProgress
        )

        findViewById<View>(R.id.username_tv).setOnClickListener {
            TestDialog.show(
                "Example Hello",
                "Ublyudok, mat' tvoyu, a nu idi syuda, govno" + " sobachye, reshil ko mne lezt'? Ti, zasranec vonyuchiy, mat' " + "tvoyu, a?",
                supportFragmentManager
            )
        }
    }

    private fun showFavoriteBook(favoriteBook: Book?) {
        // TODO
        val favGroup = findViewById<View>(R.id.gr_favorite)
        favGroup.visibility = favoriteBook?.let {
            val favoriteBookView = findViewById<View>(R.id.fav_book)
            favoriteBookView.findViewById<TextView>(R.id.title_tv)
                .text = it.title
            favoriteBookView.findViewById<TextView>(R.id.count_tv)
                .text = it.numberOfTimesRead.toString()
            View.VISIBLE
        } ?: View.GONE
    }

    override fun onRefresh() {
        viewModel.updateProfile()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.logout -> {
            logout()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    private fun showUsername(username: String) {
        findViewById<TextView>(R.id.username_tv).text = username
    }

    private fun showBooks(bookList: List<Book>) {
        findViewById<RecyclerView>(R.id.books_rv).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = BookAdapter(bookList)
        }
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
        findViewById<View>(R.id.main_layout).visibility = View.VISIBLE
    }

    private fun showError(errorText: String?) {
        errorText?.let {
            Toast.makeText(this, errorText, Toast.LENGTH_LONG)
                .show()
        }
    }
}
