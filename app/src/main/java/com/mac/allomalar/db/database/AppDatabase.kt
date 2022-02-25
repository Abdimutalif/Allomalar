package com.mac.allomalar.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mac.allomalar.db.daos.AllomaDao
import com.mac.allomalar.db.daos.AllomasDao
import com.mac.allomalar.db.daos.CenturyDao
import com.mac.allomalar.db.daos.MadrasasDao
import com.mac.allomalar.models.Century

@Database(entities = [Century::class], version = 1)
abstract class AppDatabase :RoomDatabase(){

    abstract fun centuryDao(): CenturyDao

    abstract fun allomaDao():AllomaDao

    abstract fun allomasDao():AllomasDao

    abstract fun madrasasDao():MadrasasDao

    companion object{
        private var appDatabase:AppDatabase?=null

        @Synchronized
        fun getInstance(context: Context): AppDatabase{
            if (appDatabase==null){
                appDatabase=Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "scholars_db"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return appDatabase!!
        }
    }
}