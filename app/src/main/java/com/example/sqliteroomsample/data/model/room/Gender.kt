package com.example.sqliteroomsample.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sqliteroomsample.util.Global

@Entity(tableName = Global.TABLE_GENDER)
data class Gender(
        @PrimaryKey(autoGenerate = true) val id: Int = -1,
        @ColumnInfo(name = "name") val name: String? = null
)
