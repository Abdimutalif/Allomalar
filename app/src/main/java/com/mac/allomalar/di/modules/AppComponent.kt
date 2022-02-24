package com.mac.allomalar.di.modules

import com.mac.allomalar.ui.avtivoties.AllomalarActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(allomalarActivity: AllomalarActivity)
}