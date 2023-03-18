package com.test.sharedpref.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PrefData")
data class PrefData(
    @PrimaryKey val key: String, val value: String
)