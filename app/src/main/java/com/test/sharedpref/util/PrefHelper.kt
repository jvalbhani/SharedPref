package com.test.sharedpref.util

import com.google.gson.Gson
import com.test.sharedpref.room.dao.PrefDataDao
import com.test.sharedpref.room.entity.PrefData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.Serializable

//2 mistakes leading to crash
class PrefHelper(private val prefDataDao: PrefDataDao) {

    fun get(key: String): String? {//can make this private, but kept for returning json string
        var data: String?
        runBlocking {
            data = prefDataDao.get(key)?.value // silly mistake was parsing prefData instead of value
        }
        return data
    }

    fun getOrDefault(key: String, default: Serializable): Serializable {
        return try {
            Gson().fromJson(get(key), default.javaClass) ?: default
        } catch (e: Exception) {

//was making mistake here wasn't returning default just the Log.e() statement leading to crash
//            Log.e(TAG, "getOrDefault: ", e)
            default
        }
    }

    fun putPrefData(key: String, value: Serializable) {
        GlobalScope.launch {
            prefDataDao.insert(PrefData(key, Gson().toJson(value)))
        }
    }
}
