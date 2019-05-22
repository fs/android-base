package com.flatstack.android.model.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Transaction
import com.flatstack.android.model.entities.Session

@Dao
abstract class SessionDao {
    @Insert(onConflict = REPLACE)
    abstract suspend fun insertSession(vararg session: Session)

    @Query("SELECT * FROM session LIMIT 1")
    abstract suspend fun getSession(): Session?

    @Query("DELETE FROM session")
    abstract suspend fun clearSession()

    @Transaction
    open suspend fun insert(session: Session) {
        clearSession()
        insertSession(session)
    }

    suspend fun getToken(): String = getSession()?.accessToken ?: ""
}
