package com.nbialas.listoapp.utils

import android.content.Context
import com.nbialas.listoapp.R
import java.util.concurrent.TimeUnit

object TimeFormatter {
    private const val HOUR = (60L * 60L)
    private const val SECOND = 60L
    private const val SECOND_TIME = 1000L

    fun parseTime(context: Context, time: Long): String {
        val builder = StringBuilder()
        builder.append(context.resources.getString(R.string.created))

        val secondsDifference = time / SECOND_TIME

        val days = TimeUnit.MILLISECONDS.toDays(time)
        val hours = (secondsDifference % (HOUR * 24L)) / HOUR
        val minutes = (secondsDifference % HOUR) / SECOND
        val seconds = secondsDifference % SECOND

        if (days > 0) builder.append(
            context.resources.getString(R.string.days, days.toString())
        )
        if (hours > 0) builder.append(
            context.resources.getString(R.string.hours, hours.toString())
        )
        if (minutes > 0) builder.append(
            context.resources.getString(R.string.minutes, minutes.toString())
        )

        builder.append(context.resources.getString(R.string.seconds, seconds.toString()))
        builder.append(context.resources.getString(R.string.ago))
        return builder.toString()

    }
}