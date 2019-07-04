package com.example.sqliteroomsample.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.sqliteroomsample.data.model.room.User
import com.example.sqliteroomsample.util.Global
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Single<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<User>): Single<List<Long>>

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateGender(gender: User): Completable

    @Delete
    fun deleteGender(gender: User): Completable

    @Query(GET_GENDER_BY_NAME)
    fun getGenderByName(name: String): Single<List<User>>

    @Query(GET_GENDERS)
    fun getGenders(): Single<List<User>>

    companion object {
        private const val GET_GENDERS = "SELECT * FROM ${Global.TABLE_USER}"
        private const val GET_GENDER_BY_NAME = "SELECT * FROM ${Global.TABLE_USER} WHERE name = :name"
    }
}
