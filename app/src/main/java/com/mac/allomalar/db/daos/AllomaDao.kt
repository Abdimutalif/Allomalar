package com.mac.allomalar.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.mac.allomalar.models.Alloma


@Dao
interface AllomaDao {

    @Query("Select*from alloma")
    fun getAlloma():List<Alloma>

    @Insert
    suspend fun addAlloma(alloma: Alloma)

    @Insert(onConflict =REPLACE)
    suspend fun addAllomas(list: List<Alloma>)



}