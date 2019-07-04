package com.example.sqliteroomsample.di

import com.example.sqliteroomsample.data.local.database.AppRoomDatabase
import com.example.sqliteroomsample.data.local.source.UserLocalDataSource
import com.example.sqliteroomsample.data.repository.UserLocalRepository
import com.example.sqliteroomsample.ui.activity.room.RoomViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { RoomViewModel(get()) }

    single { AppRoomDatabase.getInstance(androidContext()) }

    single { get<AppRoomDatabase>().userDao() }

    single { UserLocalDataSource(get()) }

    single { UserLocalRepository(get()) }
}
