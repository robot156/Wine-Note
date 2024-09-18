package com.winenote.core.ui.util

import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun getDateTimeFormatString(dateTime: String?, pattern: String): String {
    val zonedDateTime = dateTime?.let { ZonedDateTime.parse(dateTime) } ?: ZonedDateTime.now()
    return zonedDateTime.withZoneSameInstant(ZoneId.systemDefault())?.format(DateTimeFormatter.ofPattern(pattern)) ?: ""
}

fun getDateFormatLongTime(dateTime: String?): Long {
    val zonedDateTime = dateTime?.let { ZonedDateTime.parse(dateTime) } ?: ZonedDateTime.now()
    return zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

fun getStringFormatZonedDateTime(dateTime: String?): ZonedDateTime {
    val zonedDateTime = dateTime?.let { ZonedDateTime.parse(dateTime) } ?: ZonedDateTime.now()
    return zonedDateTime
}

fun getLongFormatZonedDateTime(epochMilli: Long): ZonedDateTime {
    return ZonedDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault())
}

fun getZonedDateTimeWithSyncZero(zonedDateTime: ZonedDateTime = ZonedDateTime.now()): ZonedDateTime {
    return zonedDateTime.withHour(0).withSecond(0).withNano(0)
}

fun getDateTimeBetweenDay(startDate: ZonedDateTime? = null, endDate: ZonedDateTime? = null): Int {
    val startDateTime = startDate ?: ZonedDateTime.now()
    val endDateTime = endDate ?: ZonedDateTime.now()
    return Duration.between(startDateTime, endDateTime).toDays().toInt()
}

fun isSameMonth(date: String?, otherDay: String?): Boolean {
    return if (otherDay == null) {
        true
    } else {
        (ZonedDateTime.parse(date).month.value == ZonedDateTime.parse(otherDay).month.value)
    }
}