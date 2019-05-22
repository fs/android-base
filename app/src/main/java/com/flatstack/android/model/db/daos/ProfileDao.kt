package com.flatstack.android.model.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.flatstack.android.profile.entities.Book
import com.flatstack.android.profile.entities.Profile

@Dao
abstract class ProfileDao {
    @Insert(onConflict = REPLACE)
    abstract suspend fun insertProfile(vararg profile: Profile)

    @Query("SELECT * FROM profile LIMIT 1")
    abstract suspend fun getProfile(): Profile?

    @Query("DELETE FROM profile")
    abstract suspend fun clearProfile()

    @Insert(onConflict = REPLACE)
    abstract suspend fun insertBooks(books: List<Book>)

    @Query("SELECT * FROM books")
    abstract suspend fun getBooks(): List<Book>

    @Query("DELETE FROM books")
    abstract suspend fun clearBooks()

    @Transaction
    open suspend fun insertProfileAndBooks(profile: Profile) {
        clear()
        insertBooks(profile.booksRead)
        insertProfile(profile)
    }

    @Transaction
    open suspend fun getProfileWithBooks(): Profile? = getProfile()?.apply {
        booksRead = getBooks()
    }

    @Transaction
    open suspend fun clear() {
        clearBooks()
        clearProfile()
    }
}
