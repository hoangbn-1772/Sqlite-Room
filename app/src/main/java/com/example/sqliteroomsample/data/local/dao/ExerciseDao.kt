package com.example.sqliteroomsample.data.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.sqliteroomsample.data.model.room.Exercise
import com.example.sqliteroomsample.util.Global
import io.reactivex.Single

interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercise(exercise: Exercise)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateExercise(exercise: Exercise)

    @Delete
    fun deleteExercise(exercise: Exercise)

    @Query(GET_EXERCISES)
    fun getExerciseByName(name: String): Single<List<Exercise>>

    @Query(GET_EXERCISE_BY_NAME)
    fun getExercises(): Single<List<Exercise>>

    companion object {
        private const val GET_EXERCISE_BY_NAME = "SELECT * FROM ${Global.TABLE_EXERCISE}"

        private const val GET_EXERCISES = "SELECT * FROM ${Global.TABLE_EXERCISE} WHERE name = :name"
    }
}
