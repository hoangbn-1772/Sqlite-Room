package com.example.sqliteroomsample.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.sqliteroomsample.data.local.dao.UserDao
import com.example.sqliteroomsample.data.model.room.User

@Database(entities = [User::class], version = AppRoomDatabase.DATABASE_VERSION)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_VERSION = 1

        private const val DATABASE_NAME = "sun_room.db"

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase = INSTANCE
            ?: synchronized(AppRoomDatabase::class) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Neu bang co thay doi, viet code tai day
            }
        }

        private val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Neu bang co thay doi, viet code tai day
            }
        }

        fun destroyDatabase() {
            INSTANCE = null
        }
    }
}
