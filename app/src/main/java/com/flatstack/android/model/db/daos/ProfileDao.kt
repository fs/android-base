package com.flatstack.android.model.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.flatstack.android.profile.entities.Profile

@Dao
abstract class ProfileDao {
    @Insert(onConflict = REPLACE)
    abstract suspend fun insertProfile(vararg profile: Profile)

    @Query("SELECT * FROM profile LIMIT 1")
    abstract suspend fun getProfile(): Profile?

    @Query("DELETE FROM profile")
    abstract suspend fun clearProfile()

    @Transaction
    open suspend fun insertUserProfile(profile: Profile) {
        clear()
        insertProfile(profile)
    }

    @Transaction
    open suspend fun clear() {
        clearProfile()
    }
}
