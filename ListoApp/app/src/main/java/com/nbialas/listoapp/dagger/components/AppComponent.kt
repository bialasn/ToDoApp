package com.nbialas.listoapp.dagger.components

import com.nbialas.listoapp.dagger.modules.ContextModule
import com.nbialas.listoapp.dagger.modules.DatabaseModule
import com.nbialas.listoapp.viewModels.DetailsViewModel
import com.nbialas.listoapp.viewModels.HomeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DatabaseModule::class,
        ContextModule::class
    ]
)
interface AppComponent {
    fun inject(into: HomeViewModel)
    fun inject(into: DetailsViewModel)
}