package com.example.sqliteroomsample.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sqliteroomsample.data.local.dao.ExerciseDao
import com.example.sqliteroomsample.data.local.dao.GenderDao
import com.example.sqliteroomsample.data.local.dao.RoutineDao
import com.example.sqliteroomsample.data.local.dao.TraineeDao
import com.example.sqliteroomsample.data.model.room.Exercise
import com.example.sqliteroomsample.data.model.room.Gender
import com.example.sqliteroomsample.data.model.room.Routine
import com.example.sqliteroomsample.data.model.room.Trainee
import com.example.sqliteroomsample.util.DateTypeConverter
import com.example.sqliteroomsample.util.ListConverter

@Database(
    entities = [Gender::class, Exercise::class, Routine::class, Trainee::class],
    version = AppRoomDatabase.DATABASE_VERSION
)
@TypeConverters(DateTypeConverter::class, ListConverter::class)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun genderDao(): GenderDao

    abstract fun exerciseDao(): ExerciseDao

    abstract fun routineDao(): RoutineDao

    abstract fun traineeDao(): TraineeDao

    companion object {
        const val DATABASE_VERSION = 1

        private const val DATABASE_NAME = "sun_room.db"

        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase = INSTANCE
            ?: synchronized(AppRoomDatabase::class) {
                Room.databaseBuilder(context.applicationContext, AppRoomDatabase::class.java, DATABASE_NAME)
                    .build()
            }

        fun destroyDatabase() {
            INSTANCE = null
        }
    }
}
