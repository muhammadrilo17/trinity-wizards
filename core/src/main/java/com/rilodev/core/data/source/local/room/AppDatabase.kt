package com.rilodev.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rilodev.core.data.source.local.entity.DataEntity

@Database(entities = [DataEntity::class], exportSchema = false, version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun appDao(): AppDao
}