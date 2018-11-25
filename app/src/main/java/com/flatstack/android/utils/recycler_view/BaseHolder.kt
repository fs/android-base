package com.flatstack.android.utils.recycler_view

import android.support.v7.widget.RecyclerView
import android.view.View

abstract class BaseHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    internal abstract fun bind(item: T)
}
