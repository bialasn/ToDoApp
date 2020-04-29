package com.nbialas.listoapp.dagger.modules

import android.content.Context
import com.nbialas.listoapp.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class ContextModule(private val application: App) {
    @Singleton
    @Provides
    fun provideContext(): Context {
        return application.applicationContext
    }


}