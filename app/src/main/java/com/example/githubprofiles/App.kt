package com.example.githubprofiles

import android.app.Application
import com.example.githubprofiles.di.appModule
import com.example.githubprofiles.di.storageModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@App)
            modules(appModule, storageModule)
        }
    }
}