package com.flatstack.android.util.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    internal abstract fun bind(item: T)
}
