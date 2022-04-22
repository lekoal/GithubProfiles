package com.example.githubprofiles

import android.app.Application
import android.content.Context
import com.example.githubprofiles.data.ProfileRetrofitImpl
import com.example.githubprofiles.domain.ProfileRepo

class App : Application() {
    val gitHubGetUsersData: ProfileRepo by lazy { ProfileRetrofitImpl() }
}

val Context.app: App
    get() = applicationContext as App