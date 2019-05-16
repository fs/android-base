package com.flatstack.android.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flatstack.android.R
import com.flatstack.android.profile.entities.Book
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_book.*

class BookViewHolder private constructor(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    companion object {
        fun create(parent: ViewGroup) = BookViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        )
    }

    fun bind(book: Book) {
        tv_title.text = book.title
        tv_count.text = book.numberOfTimesRead.toString()
    }
}
