package com.test.sharedpref.room.dao
import androidx.room.*
import com.test.sharedpref.room.entity.PrefData

@Dao
interface PrefDataDao {

    @Transaction
    @Query("Select * FROM prefdata WHERE `key` is (:key) LIMIT 1")
    fun get(key: String): PrefData?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: PrefData)

    @Query("DELETE FROM prefdata")
    fun delete()

}
