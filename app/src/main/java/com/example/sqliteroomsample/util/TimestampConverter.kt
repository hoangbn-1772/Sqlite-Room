package com.example.sqliteroomsample.util

import androidx.room.TypeConverter
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TimestampConverter {

    private val df: SimpleDateFormat = DateFormat.getDateTimeInstance() as SimpleDateFormat

    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: String?): Date? {
        if (value != null) {
        try {
                return df.parse(value)

            } catch (e: ParseException) {
                e.printStackTrace()
            }
        }
        return null
    }

    @TypeConverter
    @JvmStatic
    fun dateToTimestamp(value: Date?): String? {
        return value?.run { df.format(this) }
    }
}
