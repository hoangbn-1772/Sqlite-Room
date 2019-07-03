package com.example.sqliteroomsample.data.local.dao

import androidx.room.*
import com.example.sqliteroomsample.data.model.room.Gender
import com.example.sqliteroomsample.util.Global

@Dao
interface GenderDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGender(gender: Gender)

    @Update
    fun updateGender(gender: Gender)

    @Delete
    fun deleteGender(gender: Gender)

    @Query(GET_GENDER_BY_NAME)
    fun getGenderByName(name: String): List<Gender>

    @Query(GET_GENDERS)
    fun getGenders(): List<Gender>

    companion object {
        private const val GET_GENDERS = "SELECT * FROM ${Global.TABLE_GENDER}"
        private const val GET_GENDER_BY_NAME = "SELECT * FROM ${Global.TABLE_GENDER} WHERE name == :name"
    }
}
