package com.example.sqliteroomsample.data.repository

import com.example.sqliteroomsample.data.datasource.GenderDataSource
import com.example.sqliteroomsample.data.local.source.GenderLocalDataSource
import com.example.sqliteroomsample.data.model.room.Gender
import io.reactivex.Completable
import io.reactivex.Single

class GenderLocalRepository(private val genderLocalDataSource: GenderLocalDataSource) : GenderDataSource.Local {
    override fun insertGender(gender: Gender): Completable = genderLocalDataSource.insertGender(gender)

    override fun updateGender(gender: Gender): Completable = genderLocalDataSource.updateGender(gender)

    override fun deleteGender(gender: Gender): Completable = genderLocalDataSource.deleteGender(gender)

    override fun getGenderByName(name: String): Single<List<Gender>> = genderLocalDataSource.getGenderByName(name)

    override fun getGenders(): Single<List<Gender>> = genderLocalDataSource.getGenders()
}
