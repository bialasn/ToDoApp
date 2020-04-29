package com.nbialas.listoapp.dagger

import com.nbialas.listoapp.App
import com.nbialas.listoapp.dagger.components.AppComponent
import com.nbialas.listoapp.dagger.components.DaggerAppComponent
import com.nbialas.listoapp.dagger.modules.ContextModule
import com.nbialas.listoapp.dagger.modules.DatabaseModule


object Injector {
    lateinit var component: AppComponent

    fun init(application: App) {
        component = DaggerAppComponent.builder()
            .contextModule(ContextModule(application))
            .databaseModule(DatabaseModule(application))
            .build()
    }
}