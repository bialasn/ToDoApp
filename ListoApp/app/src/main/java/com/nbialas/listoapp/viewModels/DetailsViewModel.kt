package com.nbialas.listoapp.viewModels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nbialas.listoapp.dagger.Injector
import com.nbialas.listoapp.database.dao.ThingToDoDao
import com.nbialas.listoapp.tools.TimeFormatter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsViewModel : ViewModel() {
    @Inject
    lateinit var thingToDoDao: ThingToDoDao
    @Inject
    lateinit var contextDagger: Context
    var passedTime = MutableLiveData<String>()
    var thingID: String = ""
    var startTime: Long = -1

    init {
        Injector.component.inject(this)
    }

    fun setTime(time: Long) {
        startTime = time
            .also { calculateTime() }
    }

    fun calculateTime() =
        CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                delay(200)
                passedTime.postValue(
                    TimeFormatter.parseTime(contextDagger,
                        System.currentTimeMillis().minus(startTime)
                    )
                )
                delay(800)
            }
        }
}