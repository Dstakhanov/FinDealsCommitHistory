package com.dstakhanov.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


object DateUtility {
    val patternDate = "dd.MM.yyyy HH:mm"

    fun convertTimestampToDateTime(timestamp: Long?): String {
        if (timestamp == null) return ""

        val sdf = SimpleDateFormat(patternDate, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        val stamp = Timestamp(timestamp * 1000L)
        val netDate = Date(stamp.time)
        return sdf.format(netDate)

    }

    fun convertTimestampToTime(timestamp: Long?): String {
        if (timestamp == null) return ""
        val stamp = Timestamp(timestamp * 1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    fun convertDateToLong(date: String): Long {
        if (date == "") return 0L
        val df = SimpleDateFormat(patternDate, Locale.getDefault())
        return df.parse(date).time / 1000L
    }

    fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    fun getCurrentDateInMills(): Long {
        return Calendar.getInstance().timeInMillis
    }

}