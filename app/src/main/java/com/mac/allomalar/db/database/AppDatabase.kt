package com.mac.allomalar.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mac.allomalar.db.daos.CenturyDao
import com.mac.allomalar.models.Century

@Database(entities = [Century::class], version = 1)
abstract class AppDatabase :RoomDatabase(){
    abstract fun centuryDao(): CenturyDao

//    companion object{
//        fun getInstance(context: Context): AppDatabase{
//            return Room.databaseBuilder(
//                context,
//                AppDatabase::class.java,
//                "scholars_db"
//            ).build()
//        }
//    }
}