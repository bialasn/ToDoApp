package com.nbialas.listoapp.dagger

import com.nbialas.listoapp.App


object Injector {
    lateinit var component: AppComponent

    fun init(application: App){
        component = DaggerAppComponent.builder()
            .dbModule(DbModule(application))
            .build()
    }
}