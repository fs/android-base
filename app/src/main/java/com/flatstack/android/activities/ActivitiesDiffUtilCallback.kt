package com.flatstack.android.activities

import androidx.recyclerview.widget.DiffUtil

class ActivitiesDiffUtilCallback : DiffUtil.ItemCallback<ActivitiesViewHolderModel>() {
    override fun areItemsTheSame(
        oldItem: ActivitiesViewHolderModel,
        newItem: ActivitiesViewHolderModel
    ): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: ActivitiesViewHolderModel,
        newItem: ActivitiesViewHolderModel
    ): Boolean = oldItem == newItem
}
