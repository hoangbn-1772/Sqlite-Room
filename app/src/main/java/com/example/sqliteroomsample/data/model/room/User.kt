package com.example.sqliteroomsample.data.model.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sqliteroomsample.util.Global

@Entity(tableName = Global.TABLE_GENDER)
data class User(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @ColumnInfo(name = "name") var name: String? = null,
        @ColumnInfo(name = "address") var address: String? = null
)
