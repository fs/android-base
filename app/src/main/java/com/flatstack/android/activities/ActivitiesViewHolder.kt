package com.flatstack.android.activities

import android.view.View
import com.flatstack.android.R
import com.flatstack.android.util.recyclerview.BaseHolder
import kotlinx.android.synthetic.main.item_activity.view.*

class ActivitiesViewHolder(itemView: View) : BaseHolder<ActivitiesViewHolderModel>(itemView) {

    override fun bind(item: ActivitiesViewHolderModel) {
        with(containerView) {
            tv_event.text = item.title
            tv_body.text = item.body
            tv_created_at.text = item.createdAt
            tv_username.text = item.userName
        }
    }
}
