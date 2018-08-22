package com.hyc.wallpaper.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import com.hyc.wallpaper.Application

@Database(entities = [ItemDetail::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun itemDetailDao(): ItemDetailDao

    companion object {
        val instance: AppDatabase  by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            Room.databaseBuilder(Application.instance.applicationContext, AppDatabase::class.java, "wallpaper.db").build()
        }
    }
}