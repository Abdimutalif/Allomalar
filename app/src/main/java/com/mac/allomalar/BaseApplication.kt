package com.mac.allomalar

import android.app.AppComponentFactory
import android.app.Application
import com.mac.allomalar.di.modules.AppComponent
import com.mac.allomalar.ui.avtivoties.AllomalarActivity
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Singleton

@HiltAndroidApp
class BaseApplication : Application(){
    companion object{
        lateinit var appComponent: AppComponent
    }
    @Singleton
    override fun onCreate() {
        super.onCreate()

    }
}