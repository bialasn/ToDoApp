package com.nbialas.listoapp.viewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nbialas.listoapp.dagger.Injector
import com.nbialas.listoapp.database.dao.ThingToDoDao
import com.nbialas.listoapp.models.ThingToDo
import com.nbialas.listoapp.tools.ThingNameGenerator
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeViewModel : ViewModel() {

    @Inject
    lateinit var thingToDoDao: ThingToDoDao
    @Inject
    lateinit var contextDagger: Context

    init {
        Injector.component.inject(this)
    }

    fun updateThingCounter(item: ThingToDo) = viewModelScope.launch {
        thingToDoDao.updateThingToDo(item.uniqueID)
    }

    fun removeItem(item: ThingToDo) =
        viewModelScope.launch {
            thingToDoDao.delete(item)
        }

    fun addThingToDo() =
        viewModelScope.launch {
            thingToDoDao.saveThingToDo(
                ThingNameGenerator.generateName(
                    contextDagger
                )
            )
        }

}