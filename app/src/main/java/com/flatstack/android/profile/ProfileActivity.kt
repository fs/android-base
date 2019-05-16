package com.flatstack.android.profile

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.flatstack.android.R
import com.flatstack.android.Router
import com.flatstack.android.profile.entities.Book
import com.flatstack.android.util.observeBy
import com.flatstack.android.util.provideViewModel
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.book_item.view.*
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
                    showUsername(it.username)
                    showFavoriteBook(it.favoriteBook)
                    showBooks(it.booksRead)
                },
                onError = ::showError,
                onLoading = ::visibleProgress)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.logout -> {
            logout()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onRefresh() {
        viewModel.updateProfile()
    }

    private fun showFavoriteBook(favoriteBook: Book?) {
        // TODO
        val favGroup = gr_favorite
        favGroup.visibility = favoriteBook?.let {
            val favoriteBookView = inc_fav_book
            favoriteBookView.tv_title.text = it.title
            favoriteBookView.tv_count.text = it.numberOfTimesRead.toString()
            View.VISIBLE
        } ?: View.GONE
    }

    private fun showUsername(username: String) {
        tv_username.text = username
    }

    private fun showBooks(bookList: List<Book>) {
        rv_books.apply {
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
        cl_main.visibility = View.VISIBLE
    }

    private fun showError(errorText: String?) {
        errorText?.let {
            Toast.makeText(this, errorText, Toast.LENGTH_LONG).show()
        }
    }
}
