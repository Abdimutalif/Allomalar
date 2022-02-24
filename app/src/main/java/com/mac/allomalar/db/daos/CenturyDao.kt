package com.mac.allomalar.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.mac.allomalar.models.Century

@Dao
interface CenturyDao {
    @Query("Select * from centuries")
    fun getAllCenturies(): List<Century>
}