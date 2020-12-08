package com.flatstack.android.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.flatstack.android.R

class ActivitiesAdapter :
    PagedListAdapter<ActivitiesViewHolderModel, ActivitiesViewHolder>(ActivitiesDiffUtilCallback()) {

    override fun onBindViewHolder(holder: ActivitiesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ActivitiesViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_activity, parent, false)
    )
}
