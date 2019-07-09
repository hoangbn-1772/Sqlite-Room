package com.example.sqliteroomsample.data.local.dao

import androidx.room.*
import com.example.sqliteroomsample.data.model.room.User
import com.example.sqliteroomsample.util.Global
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertUser(user: User): Single<Long>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertUsers(users: List<User>): Single<List<Long>>

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun updateUser(user: User): Completable

    @Delete
    fun deleteUser(user: User): Single<Int>

    @Query(GET_USERS_BY_NAME)
    fun getUserByName(name: String): Flowable<MutableList<User>>

    @Query(GET_USERS)
    fun getUsers(): Flowable<MutableList<User>>

    companion object {
        private const val GET_USERS = "SELECT * FROM ${Global.TABLE_USER}"
        private const val GET_USERS_BY_NAME = "SELECT * FROM ${Global.TABLE_USER} WHERE name = :name"
        private const val SORT_USERS_BY_TIME = "SELECT * FROM ${Global.TABLE_USER} ORDER BY datetime(joined_date)"
        private const val GET_USER_NAME = "SELECT name, joined_date FROM ${Global.TABLE_USER}"
    }
}
