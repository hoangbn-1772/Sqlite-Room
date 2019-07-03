package com.example.sqliteroomsample.data.datasource

import com.example.sqliteroomsample.data.model.room.Gender
import io.reactivex.Completable
import io.reactivex.Single

interface GenderDataSource {
    interface Remote {}

    interface Local {
        fun insertGender(gender: Gender): Completable

        fun updateGender(gender: Gender): Completable

        fun deleteGender(gender: Gender): Completable

        fun getGenderByName(name: String): Single<List<Gender>>

        fun getGenders(): Single<List<Gender>>
    }
}
