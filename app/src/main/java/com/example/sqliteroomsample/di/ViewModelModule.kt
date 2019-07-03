package com.example.sqliteroomsample.di

import com.example.sqliteroomsample.ui.activity.room.RoomViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { RoomViewModel(get()) }
}
