package com.example.githubprofiles.di

import com.example.githubprofiles.di.db.DBProfileListModule
import com.example.githubprofiles.di.web.WebProfileListModule
import com.example.githubprofiles.ui.profilelist.ProfileListFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DBProfileListModule::class,
        WebProfileListModule::class
    ]
)
interface ProfileListDependenciesComponent {
    fun inject(profileListFragment: ProfileListFragment)
}