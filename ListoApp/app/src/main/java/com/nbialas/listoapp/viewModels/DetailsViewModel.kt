package com.nbialas.listoapp.viewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.nbialas.listoapp.base.BaseViewModel
import com.nbialas.listoapp.dagger.Injector
import com.nbialas.listoapp.database.dao.ThingToDoDao
import com.nbialas.listoapp.models.ThingToDo
import com.nbialas.listoapp.tools.TimeFormatter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class DetailsViewModel : BaseViewModel() {
    @Inject
    lateinit var thingToDoDao: ThingToDoDao

    @Inject
    lateinit var contextDagger: Context

    var passedTime = MutableLiveData<String>()
    var thing = MutableLiveData<ThingToDo>()
    var thingID: String = ""

    init {
        Injector.component.inject(this)
    }

    fun setThingId(id: String) {
        thingID = id
    }

    fun getSingleThingToDo() {
        rxDisposer.add(
            thingToDoDao.getSingleThing(thingID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterSuccess { singleThing ->
                    thing.postValue(singleThing)
                    calculateTime(singleThing.creationDate)
                }
                .subscribe()
        )

    }

    private fun calculateTime(time: Long) {
        rxDisposer.add(
            Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    passedTime.postValue(
                        TimeFormatter.parseTime(
                            contextDagger,
                            System.currentTimeMillis().minus(time)
                        )
                    )
                }
        )
    }

}