package com.example.githubprofiles

import android.app.Application
import com.example.githubprofiles.data.MockUserProfileGetDataApiImpl
import com.example.githubprofiles.repo.UserProfileGetDataApi

class App : Application() {
    private val userProfileGetDataApiImpl:
            UserProfileGetDataApi by lazy {
                MockUserProfileGetDataApiImpl()
    }
}