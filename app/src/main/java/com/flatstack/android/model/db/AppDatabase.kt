package com.flatstack.android.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flatstack.android.model.db.daos.ProfileDao
import com.flatstack.android.model.db.daos.SessionDao
import com.flatstack.android.model.entities.Session
import com.flatstack.android.profile.entities.Profile

@Database(
    entities = [Profile::class, Session::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun profileDao(): ProfileDao
    abstract fun sessionDao(): SessionDao
}
