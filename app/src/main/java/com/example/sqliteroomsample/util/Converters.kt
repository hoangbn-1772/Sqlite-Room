package com.example.sqliteroomsample.util

import androidx.room.TypeConverter
import java.util.*

object Converters {

    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    @JvmStatic
    fun dateToTimestamp(date: Date?): Long? {
        return (if (date == null) null else date.time)?.toLong()
    }
}
