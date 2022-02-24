package com.mac.allomalar.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "madrasas")
data class Madrasas(
    @ColumnInfo(name="century_Id")val century_id: Int,
    @PrimaryKey val id: Int,
    @ColumnInfo(name="madrasas_name")val name: String
)