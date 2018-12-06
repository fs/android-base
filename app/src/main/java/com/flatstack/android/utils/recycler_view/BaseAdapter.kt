package com.flatstack.android.utils.recycler_view

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

import rx.functions.Func1

class BaseAdapter<T, VH : BaseHolder<T>> @JvmOverloads constructor(
    private val items: MutableList<T>,
    private val func0: Func1<ViewGroup, VH>,
    private val onItemClickListener: OnItemClickListener<T>? = null
) : RecyclerView.Adapter<VH>() {

    val data: List<T>
        get() = items

    override fun onCreateViewHolder(viewGroup: ViewGroup, holderType: Int): VH {
        val holder = func0.call(viewGroup)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                onItemClickListener.onItemClick(data[position])
            }
        }
        return holder
    }

    override fun onBindViewHolder(vh: VH, position: Int) {
        vh.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun add(item: T) {
        this.items.add(item)
        notifyItemInserted(items.size - 1)
    }
}