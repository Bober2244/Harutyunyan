package com.example.tinkofflabproject

import android.app.Application
import com.example.tinkofflabproject.di.apiModule
import com.example.tinkofflabproject.di.appModule
import com.example.tinkofflabproject.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule, apiModule, databaseModule)
        }
    }
}