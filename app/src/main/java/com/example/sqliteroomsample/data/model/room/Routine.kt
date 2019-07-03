package com.example.sqliteroomsample.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.sqliteroomsample.util.Global
import com.example.sqliteroomsample.util.ListConverter
import java.util.*

@Entity(tableName = Global.TABLE_ROUTINE)
data class Routine(
        @PrimaryKey(autoGenerate = true) val routineId: Int,
        @ColumnInfo(name = "due_day") val dueDay: Date,
        @TypeConverters(ListConverter::class) val exercises: List<Exercise>
)
