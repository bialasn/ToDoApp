package com.nbialas.listoapp.dagger

import com.nbialas.listoapp.viewModels.DetailsViewModel
import com.nbialas.listoapp.viewModels.HomeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DbModule::class
    ]
)
interface AppComponent {
    fun inject(into: HomeViewModel)
    fun inject(into: DetailsViewModel)
}