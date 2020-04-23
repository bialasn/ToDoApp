package com.nbialas.listoapp

import android.app.Application
import com.nbialas.listoapp.dagger.Injector

class App :Application(){
    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
    }
}