package com.example.sqliteroomsample.di

import com.example.sqliteroomsample.data.local.database.AppRoomDatabase
import com.example.sqliteroomsample.data.local.source.GenderLocalDataSource
import com.example.sqliteroomsample.data.repository.GenderLocalRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single { AppRoomDatabase.getInstance(androidContext()) }

    single { get<AppRoomDatabase>().genderDao() }

    single { GenderLocalRepository(get()) }

    single { GenderLocalDataSource(get()) }
}
