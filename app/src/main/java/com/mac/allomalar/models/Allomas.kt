package com.mac.allomalar.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "allomas")
data class Allomas(
    @PrimaryKey val id: Int,
    @ColumnInfo(name="alloma_image")val image: String,
    @ColumnInfo(name="alloma_madrasa")val madrasa_alloma: String,
    @ColumnInfo(name="alloma_name")val name: String
)