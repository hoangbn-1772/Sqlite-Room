package com.example.sqliteroomsample.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.sqliteroomsample.data.local.dao.UserDao
import com.example.sqliteroomsample.data.model.room.User
import com.example.sqliteroomsample.util.Converters
import com.example.sqliteroomsample.util.Global
import com.example.sqliteroomsample.util.TimestampConverter

@Database(entities = [User::class], version = AppRoomDatabase.DATABASE_VERSION)
@TypeConverters(Converters::class)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_VERSION = 2

        private const val DATABASE_NAME = "sun_room.db"

        private const val DATABASE_UPDATE = "ALTER TABLE ${Global.TABLE_USER} ADD COLUMN gender INTEGER"

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase = INSTANCE
            ?: synchronized(AppRoomDatabase::class) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    DATABASE_NAME
                ).addMigrations(MIGRATION_1_2).build()
            }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // If schema change, write code to update here
            }
        }

        private val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(DATABASE_UPDATE)
            }
        }

        private const val SQL_CREATE_TABLE_NEW = "CREATE TABLE ${Global.TABLE_USER_NEW}(id TEXT, name TEXT, address TEXT, PRIMARY KEY(id))"
        private const val SQL_COPY = "INSERT INTO ${Global.TABLE_USER_NEW}(id, name, address) SELECT id, name, address FROM ${Global.TABLE_USER}"
        private const val SQL_DROP = "DROP TABLE ${Global.TABLE_USER}"
        private const val SQL_CHANGE_NAME = "ALTER TABLE ${Global.TABLE_USER_NEW} RENAME TO ${Global.TABLE_USER}"
        private val MIGRATION_1_4: Migration = object : Migration(1, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create the new table
                database.execSQL(SQL_CREATE_TABLE_NEW)

                //Copy the data
                database.execSQL(SQL_COPY)

                // Remove the old table
                database.execSQL(SQL_DROP)

                // Change the table name
                database.execSQL(SQL_CHANGE_NAME)
            }
        }

        fun destroyDatabase() {
            INSTANCE = null
        }
    }
}
