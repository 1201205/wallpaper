package com.hyc.wallpaper

import android.app.Application
import android.arch.persistence.room.Room
import com.hyc.wallpaper.model.AppDatabase
import kotlin.properties.Delegates

class Application : Application() {
    companion object {
        var instance:com.hyc.wallpaper.Application by Delegates.notNull()
    }
    override fun onCreate() {
        super.onCreate()
        instance=this
    }
}