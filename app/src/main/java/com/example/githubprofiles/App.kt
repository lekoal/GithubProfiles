package com.example.githubprofiles

import android.app.Application
import com.example.githubprofiles.di.dbModule
import com.example.githubprofiles.di.mockModule
import com.example.githubprofiles.di.storageModule
import com.example.githubprofiles.di.webModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(webModule, dbModule, mockModule, storageModule)
        }
    }
}