package com.example.sqliteroomsample.data.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.sqliteroomsample.data.model.room.Trainee
import com.example.sqliteroomsample.util.Global

interface TraineeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTrainee(trainee: Trainee)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateTrainee(trainee: Trainee)

    @Delete
    fun deleteTrainee(trainee: Trainee)

    @Query(GET_TRAINEE_BY_NAME)
    fun getTraineeByName(name: String): List<Trainee>

    @Query(GET_TRAINEES)
    fun getTrainees(): List<Trainee>

    companion object {
        private const val GET_TRAINEES = "SELECT * FROM ${Global.TABLE_TRAINEE}"

        private const val GET_TRAINEE_BY_NAME = "SELECT * FROM ${Global.TABLE_TRAINEE} WHERE name = :nameToFind"
    }
}
