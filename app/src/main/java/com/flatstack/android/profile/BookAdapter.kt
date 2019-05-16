package com.flatstack.android.profile

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.flatstack.android.profile.entities.Book

class BookAdapter(private val bookList: List<Book>) : RecyclerView.Adapter<BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BookViewHolder.create(parent)

    override fun getItemCount(): Int =
        bookList.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) =
        holder.bind(bookList[position])
}
