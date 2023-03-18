package com.test.sharedpref.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.sharedpref.room.dao.PrefDataDao
import com.test.sharedpref.room.entity.PrefData

@Database(
    entities = [PrefData::class],
    version = 2,
    exportSchema = false
)
abstract class SharedPrefDatabase: RoomDatabase() {
    abstract fun getPrefsDao(): PrefDataDao
}
