package com.example.sqliteroomsample.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sqliteroomsample.util.Global

@Entity(tableName = Global.TABLE_EXERCISE)
data class Exercise(
        @PrimaryKey(autoGenerate = true) val id: Int = -1,
        val name: String? = null,
        val repetitions: Int,
        @ColumnInfo(name = "machine_name") val machineName: String? = null,
        val liftedWeight: Int
)
