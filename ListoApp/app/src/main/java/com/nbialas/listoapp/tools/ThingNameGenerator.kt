package com.nbialas.listoapp.tools

import android.content.Context
import androidx.core.content.ContextCompat
import com.nbialas.listoapp.R
import com.nbialas.listoapp.models.ThingToDo
import java.util.*

object ThingNameGenerator {

    fun generateName(context: Context): ThingToDo {
        return ThingToDo(
            name = context.getString(R.string.thing_to_do_uniqId, generateUuid().takeLast(8)),
            uniqueID = generateUuid(),
            creationDate = System.currentTimeMillis(),
            color = generateBackgroundColor(context),
            openCounter = 0
        )
    }

    private fun generateUuid() = UUID.randomUUID().toString()

    private fun generateBackgroundColor(context: Context) = listOf(
        ContextCompat.getColor(context, R.color.pink),
        ContextCompat.getColor(context, R.color.uglyRed),
        ContextCompat.getColor(context, R.color.uglyBlue),
        ContextCompat.getColor(context, R.color.niceBlue),
        ContextCompat.getColor(context, R.color.uglyGreen)
    ).random()
}