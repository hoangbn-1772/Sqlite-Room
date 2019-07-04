package com.example.sqliteroomsample.data.repository

import com.example.sqliteroomsample.data.datasource.UserDataSource
import com.example.sqliteroomsample.data.local.source.UserLocalDataSource
import com.example.sqliteroomsample.data.model.room.User
import io.reactivex.Completable
import io.reactivex.Single

class UserLocalRepository(private val userLocalDataSource: UserLocalDataSource) : UserDataSource.Local {

    override fun insertUser(user: User): Single<Long> = userLocalDataSource.insertUser(user)

    override fun updateUser(user: User): Completable = userLocalDataSource.updateUser(user)

    override fun deleteUser(user: User): Completable = userLocalDataSource.deleteUser(user)

    override fun getUserByName(name: String): Single<List<User>> = userLocalDataSource.getUserByName(name)

    override fun getUsers(): Single<List<User>> = userLocalDataSource.getUsers()
}
