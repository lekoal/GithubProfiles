package com.example.githubprofiles.di

import com.example.githubprofiles.di.db.DBProfileDetailsModule
import com.example.githubprofiles.di.web.WebProfileDetailsModule
import com.example.githubprofiles.ui.profiledetails.ProfileDetailsFragment
import com.example.githubprofiles.utils.PresenterStore
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DBProfileDetailsModule::class,
        WebProfileDetailsModule::class,
        StorageModule::class
    ]
)
interface ProfileDetailsDependenciesComponent {
    fun inject(profileDetailsFragment: ProfileDetailsFragment)
    fun getPresenterStore(): PresenterStore
}