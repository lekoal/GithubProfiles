package com.example.githubprofiles

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.example.githubprofiles.data.ProfileRetrofitImpl
import com.example.githubprofiles.domain.ProfileRepo
import com.example.githubprofiles.utils.PresenterStore

class App : Application() {
    val gitHubGetUsersData: ProfileRepo by lazy { ProfileRetrofitImpl() }
    val presenterStore by lazy { PresenterStore() }
}

val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireActivity().app