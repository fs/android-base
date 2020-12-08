package com.flatstack.android.activities

import android.view.View
import com.flatstack.android.R
import com.flatstack.android.util.recyclerview.BaseHolder
import kotlinx.android.synthetic.main.item_activity.view.*
import java.text.SimpleDateFormat
import java.util.*

class ActivitiesViewHolder(itemView: View) : BaseHolder<ActivitiesViewHolderModel>(itemView) {

    override fun bind(item: ActivitiesViewHolderModel) {
        with(containerView) {
            tv_event.text = item.title
            tv_body.text = item.body
            tv_created_at.text = SimpleDateFormat("HH:mm, dd MMM yyyy", Locale.ENGLISH).format(
                SimpleDateFormat("yyyy-mm-dd'T'HH:MM:ss'Z'", Locale.ENGLISH).parse(item.createdAt)
            )
            tv_username.text = itemView.context.getString(
                R.string.username_mask,
                item.user.firstName,
                item.user.lastName
            )
            tv_id.text = itemView.context.getString(R.string.id_mask, item.id)
        }
    }
}
