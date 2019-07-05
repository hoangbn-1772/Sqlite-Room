package com.example.sqliteroomsample.data.local.source

import com.example.sqliteroomsample.data.datasource.UserDataSource
import com.example.sqliteroomsample.data.local.dao.UserDao
import com.example.sqliteroomsample.data.model.room.User
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class UserLocalDataSource(private val userDao: UserDao) : UserDataSource.Local {

    override fun insertUser(user: User): Single<Long> = userDao.insertUser(user)

    override fun updateUser(user: User): Completable = userDao.updateUser(user)

    override fun deleteUser(user: User): Single<Int> = userDao.deleteUser(user)

    override fun getUserByName(name: String): Flowable<MutableList<User>> = userDao.getUserByName(name)

    override fun getUsers(): Flowable<MutableList<User>> = userDao.getUsers()
}
