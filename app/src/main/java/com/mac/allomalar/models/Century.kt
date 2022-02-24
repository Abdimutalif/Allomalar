package com.mac.allomalar.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "centuries")
data class Century(
    @ColumnInfo(name = "century_name") val century: String,
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "sum_madrasa") val sum_madrasa: String
)