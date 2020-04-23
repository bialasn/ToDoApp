package com.nbialas.listoapp.tools

object TimeTools {
    private const val HOUR = (60L * 60L)
    private const val SECOND = 60L
    private const val ONE_SECOND = 1000L

    fun parseTime(time: Long): String {
        val difference = time / ONE_SECOND
        val h = (difference % (HOUR * 24L)) / HOUR
        val m = (difference % HOUR) / SECOND
        val s = difference % SECOND
        return " $h godz $m min $s sek"
    }
}