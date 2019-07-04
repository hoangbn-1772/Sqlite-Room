package com.example.sqliteroomsample.data.local.source

import com.example.sqliteroomsample.data.datasource.UserDataSource
import com.example.sqliteroomsample.data.local.dao.UserDao
import com.example.sqliteroomsample.data.model.room.User
import io.reactivex.Completable
import io.reactivex.Single

class UserLocalDataSource(private val userDao: UserDao) : UserDataSource.Local {

    override fun insertUser(user: User): Single<Long> = userDao.insertUser(user)

    override fun updateUser(user: User): Completable = userDao.updateGender(user)

    override fun deleteUser(user: User): Completable = userDao.deleteGender(user)

    override fun getUserByName(name: String): Single<List<User>> = userDao.getGenderByName(name)

    override fun getUsers(): Single<List<User>> = userDao.getGenders()
}
