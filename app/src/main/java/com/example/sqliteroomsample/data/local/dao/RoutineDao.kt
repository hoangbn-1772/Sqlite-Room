package com.example.sqliteroomsample.data.local.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.sqliteroomsample.data.model.room.Routine
import com.example.sqliteroomsample.util.Global
import java.util.Date

interface RoutineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoutine(routine: Routine)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateRoutine(routine: Routine)

    @Delete
    fun deleteRoutine(routine: Routine)

    @Query(GET_ROUTINES)
    fun getRoutine(): List<Routine>

    @Query(GET_ROUTINES_BY_DATE)
    fun getRoutineByDueDate(due: Date): List<Routine>

    companion object {
        private const val GET_ROUTINES = "SELECT * FROM ${Global.TABLE_ROUTINE}"

        private const val GET_ROUTINES_BY_DATE = "SELECT * FROM ${Global.TABLE_ROUTINE} WHERE due_day >= :due"
    }
}
