package com.example.sqliteroomsample.data.local.source

import com.example.sqliteroomsample.data.datasource.GenderDataSource
import com.example.sqliteroomsample.data.local.dao.GenderDao
import com.example.sqliteroomsample.data.model.room.Gender
import io.reactivex.Completable
import io.reactivex.Single

class GenderLocalDataSource(private val genderDao: GenderDao) : GenderDataSource.Local {
    override fun insertGender(gender: Gender): Completable = genderDao.insertGender(gender)

    override fun updateGender(gender: Gender): Completable = genderDao.updateGender(gender)

    override fun deleteGender(gender: Gender): Completable = genderDao.deleteGender(gender)

    override fun getGenderByName(name: String): Single<List<Gender>> = genderDao.getGenderByName(name)

    override fun getGenders(): Single<List<Gender>> = genderDao.getGenders()
}
