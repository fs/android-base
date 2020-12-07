package com.flatstack.android.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.flatstack.android.R

class ActivitiesAdapter :
    PagedListAdapter<ActivitiesViewHolderModel, ActivitiesViewHolder>(ActivitiesDiffUtilCallback()) {

    private val activities = mutableListOf<ActivitiesViewHolderModel>()

    override fun getItemCount(): Int = activities.count()

    override fun onBindViewHolder(holder: ActivitiesViewHolder, position: Int) {
        holder.bind(activities[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ActivitiesViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_activity, parent, false)
    )
}
