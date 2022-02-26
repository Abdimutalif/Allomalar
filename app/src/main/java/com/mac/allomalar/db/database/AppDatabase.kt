package com.mac.allomalar.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
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
}