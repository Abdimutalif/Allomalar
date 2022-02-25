package com.mac.allomalar.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mac.allomalar.models.Madrasa

@Dao
interface MadrasasDao {

    @Insert(onConflict = REPLACE)
    suspend fun addMadrasas(list: List<Madrasa>)

    @Query("Select*from madrasas")
    fun getAllMadrasas():List<Madrasa>
}