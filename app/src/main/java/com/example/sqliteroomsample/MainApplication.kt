package com.example.sqliteroomsample

import android.app.Application
import com.example.sqliteroomsample.di.repositoryModule
import com.example.sqliteroomsample.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()

            androidContext(this@MainApplication)

            modules(viewModelModule, repositoryModule)
        }
    }
}
