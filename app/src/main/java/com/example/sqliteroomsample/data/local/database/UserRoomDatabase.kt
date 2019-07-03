package com.example.sqliteroomsample.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sqliteroomsample.data.local.dao.GenderDAO
import com.example.sqliteroomsample.data.model.room.Gender

@Database(entities = [Gender::class], version = UserRoomDatabase.DATABASE_VERSION)
abstract class UserRoomDatabase : RoomDatabase() {
    abstract class genderDao() : GenderDAO

    companion object {
        const val DATABASE_VERSION = 1

        private const val DATABASE_NAME = "sun_room.db"

        private var instance: UserRoomDatabase? = null

        fun getInstance(context: Context): UserRoomDatabase = instance
                ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                    context,
                    UserRoomDatabase::class.java,
                    DATABASE_NAME
            ).enableMultiInstanceInvalidation().build()
        }
    }
}
