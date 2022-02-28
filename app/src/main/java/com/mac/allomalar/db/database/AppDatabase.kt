package com.mac.allomalar.db.database

import android.content.Context
import androidx.room.*
import com.mac.allomalar.db.daos.*
import com.mac.allomalar.models.*
import com.mac.allomalar.utils.Converters

@Database(
    entities = [AllomaAndSubjects::class, Alloma::class, Century::class, MadrasaAndAllomas::class, Madrasa::class, Subject::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun centuryDao(): CenturyDao
    abstract fun allomaAndSubjectDao(): AllomaAndSubjectsDao
    abstract fun allomaDao(): AllomaDao
    abstract fun madrasaAndAllomasDao(): MadrasaAndAllomasDao
    abstract fun madrasaDao(): MadrasaDao
    abstract fun subjectDao(): SubjectDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "my_database_allomalar_new"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}