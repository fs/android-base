package com.flatstack.android.profile.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "profile")
data class Profile(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @SerializedName("username")
    val username: String,
    @Embedded(prefix = "book")
    @SerializedName("favorite_book") var favoriteBook: Book?
) {
    @Ignore
    @SerializedName("books_read")
    var booksRead: List<Book> = listOf()
}
