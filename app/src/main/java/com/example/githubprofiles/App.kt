package com.example.githubprofiles

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.example.githubprofiles.di.DaggerProfileDetailsDependenciesComponent
import com.example.githubprofiles.di.DaggerProfileListDependenciesComponent
import com.example.githubprofiles.di.ProfileDetailsDependenciesComponent
import com.example.githubprofiles.di.ProfileListDependenciesComponent

class App : Application() {
    lateinit var profileListDependenciesComponent: ProfileListDependenciesComponent
    lateinit var profileDetailsDependenciesComponent: ProfileDetailsDependenciesComponent

    override fun onCreate() {
        super.onCreate()

        profileListDependenciesComponent = DaggerProfileListDependenciesComponent
            .builder()
            .build()

        profileDetailsDependenciesComponent = DaggerProfileDetailsDependenciesComponent
            .builder()
            .build()
    }
}

val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireActivity().app