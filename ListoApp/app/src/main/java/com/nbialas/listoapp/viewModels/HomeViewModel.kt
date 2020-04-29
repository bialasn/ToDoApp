package com.nbialas.listoapp.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nbialas.listoapp.base.BaseViewModel
import com.nbialas.listoapp.dagger.Injector
import com.nbialas.listoapp.database.dao.ThingToDoDao
import com.nbialas.listoapp.models.ThingToDo
import com.nbialas.listoapp.utils.ThingGenerator
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeViewModel : BaseViewModel() {
    @Inject
    lateinit var thingToDoDao: ThingToDoDao

    @Inject
    lateinit var context: Context

    lateinit var thingToDoList: LiveData<List<ThingToDo>>

    init {
        Injector.component.inject(this)
        getThingList()
    }


    private fun getThingList() {
        thingToDoList = thingToDoDao.getAllThingsToDo()
    }

    fun updateThingCounter(item: ThingToDo) = viewModelScope.launch {
        thingToDoDao.updateThingToDo(item.uniqueID)
    }

    fun removeThing(item: ThingToDo) =
        viewModelScope.launch {
            thingToDoDao.delete(item)
        }

    fun addThingToDo() =
        viewModelScope.launch {
            thingToDoDao.insertThingToDo(
                ThingGenerator.generateThing(
                    context
                )
            )
        }

}