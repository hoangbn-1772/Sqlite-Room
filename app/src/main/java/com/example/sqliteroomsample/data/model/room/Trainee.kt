package com.example.sqliteroomsample.data.model.room

import androidx.room.*
import com.example.sqliteroomsample.util.Global

@Entity(tableName = Global.TABLE_TRAINEE,
        indices = [
            Index("name"),
            Index("age")
        ],
        foreignKeys = [
            ForeignKey(
                    entity = Gender::class,
                    parentColumns = ["id"],
                    childColumns = ["gender"]
            )
        ])
data class Trainee(
        @PrimaryKey(autoGenerate = true) val id: Int,
        val name: String,
        val age: Int,
        val gender: Int?,
        @Embedded val routine: Routine
)
