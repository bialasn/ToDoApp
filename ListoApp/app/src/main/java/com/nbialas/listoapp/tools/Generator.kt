package com.nbialas.listoapp.tools

import android.content.Context
import androidx.core.content.ContextCompat
import com.nbialas.listoapp.R
import com.nbialas.listoapp.models.ThingToDo
import java.util.*


object Generator {

    fun thingNameGenerator(context: Context): ThingToDo {
        return ThingToDo(
            name = context.getString(R.string.thing_to_do_uniqId, uuid().takeLast(8)),
            uniqueID = uuid(),
            creationDate = System.currentTimeMillis(),
            color = colorBgList(context).random(),
            openCounter = 0
        )
    }

    private fun uuid() = UUID.randomUUID().toString().replace("-", "")


    private fun colorBgList(context: Context) = listOf(
        ContextCompat.getColor(context, R.color.pink),
        ContextCompat.getColor(context, R.color.uglyRed),
        ContextCompat.getColor(context, R.color.uglyBlue),
        ContextCompat.getColor(context, R.color.niceBlue),
        ContextCompat.getColor(context, R.color.uglyGreen)
    )

}