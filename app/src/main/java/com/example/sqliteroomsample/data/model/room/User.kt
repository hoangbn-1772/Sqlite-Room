package com.example.sqliteroomsample.data.model.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.sqliteroomsample.util.Global
import kotlinx.android.parcel.Parcelize
import java.time.OffsetDateTime
import java.util.*

@Entity(
    tableName = Global.TABLE_USER,
    indices = [Index("name", unique = true)]
)
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "address") var address: String? = null,
    @Ignore var phone: String = "PHONE DEFAULT",
    var joined_date: OffsetDateTime? = null
) : Parcelable
