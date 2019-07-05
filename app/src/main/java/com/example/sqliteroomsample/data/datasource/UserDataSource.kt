package com.example.sqliteroomsample.data.datasource

import com.example.sqliteroomsample.data.model.room.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface UserDataSource {
    interface Remote {}

    interface Local {

        fun insertUser(user: User): Single<Long>

        fun updateUser(user: User): Completable

        fun deleteUser(user: User): Single<Int>

        fun getUserByName(name: String): Flowable<MutableList<User>>

        fun getUsers(): Flowable<MutableList<User>>
    }
}
