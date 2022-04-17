package com.example.githubprofiles

import android.app.Application
import android.content.Context
import com.example.githubprofiles.data.MockUserProfileGetDataApiImpl
import com.example.githubprofiles.repo.UserProfileGetDataApi

class App : Application() {
    private val userProfileGetDataApiImpl:
            UserProfileGetDataApi by lazy {
                MockUserProfileGetDataApiImpl()
    }

    val Context.app: App
        get() = applicationContext as App
}