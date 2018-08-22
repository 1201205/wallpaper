package com.hyc.wallpaper

import android.app.Application
import android.os.Debug
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.hyc.wallpaper.model.AppDatabase
import kotlin.properties.Delegates

class Application : Application() {
    companion object {
        var instance:com.hyc.wallpaper.Application by Delegates.notNull()
    }
    override fun onCreate() {
        super.onCreate()
        instance=this
        Fresco.initialize(this)
//        Debug.startMethodTracing("hyc")
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}