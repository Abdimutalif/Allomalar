package com.mac.allomalar.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import com.mac.allomalar.database.AppDatabase
import com.mac.allomalar.database.dao.UserDao
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "my_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao = appDatabase.userDao()
}