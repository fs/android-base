package com.flatstack.android.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.paging.PagedList
import com.flatstack.android.R
import com.flatstack.android.util.observeBy
import com.flatstack.android.util.provideViewModel
import kotlinx.android.synthetic.main.activity_user_activities.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein

class UserActivitiesActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by kodein()

    private val viewModel: ActivitiesViewModel by provideViewModel()
    private val adapter = ActivitiesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_activities)
        initRecycler()

        viewModel.activities.observeBy(
            this,
            onNext = ::fetchActivities,
            onLoading = ::showProgress,
            onError = ::showError
        )
    }

    private fun fetchActivities(pagedList: PagedList<ActivitiesViewHolderModel?>) {
        adapter.submitList(pagedList)
    }

    private fun showProgress(isLoading: Boolean) {
        pb_progress.isVisible = isLoading
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun initRecycler() {
        rv_activities.adapter = adapter
    }
}
