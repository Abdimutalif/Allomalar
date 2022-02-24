package com.mac.allomalar.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.mac.allomalar.models.Madrasas

@Dao
interface MadrasasDao {
    @Query("Select*from madrasas")
    fun getAllMadrasas():List<Madrasas>
}