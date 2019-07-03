package com.example.sqliteroomsample.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.sqliteroomsample.data.model.room.Gender
import com.example.sqliteroomsample.util.Global
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface GenderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGender(gender: Gender): Completable

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateGender(gender: Gender): Completable

    @Delete
    fun deleteGender(gender: Gender): Completable

    @Query(GET_GENDER_BY_NAME)
    fun getGenderByName(name: String): Single<List<Gender>>

    @Query(GET_GENDERS)
    fun getGenders(): Single<List<Gender>>

    companion object {
        private const val GET_GENDERS = "SELECT * FROM ${Global.TABLE_GENDER}"
        private const val GET_GENDER_BY_NAME = "SELECT * FROM ${Global.TABLE_GENDER} WHERE name = :name"
    }
}
