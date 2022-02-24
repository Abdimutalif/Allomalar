package com.mac.allomalar.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.mac.allomalar.models.Allomas

@Dao
interface AllomasDao {

    @Query("Select*from allomas")
    fun getAllAllomas():List<Allomas>

}