package com.rilodev.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_table")
data class DataEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(index = true, name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String?,
)