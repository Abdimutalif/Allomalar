package com.mac.allomalar.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mac.allomalar.models.Allomas

@Dao
interface AllomasDao {


    @Insert(onConflict = REPLACE)
    suspend fun addAllomas(list: List<Allomas>)

    @Query("Select*from allomas")
    fun getAllAllomas():List<Allomas>

}