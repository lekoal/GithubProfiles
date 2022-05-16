package com.example.githubprofiles.di

import com.example.githubprofiles.di.db.DBProfileListModule
import com.example.githubprofiles.di.web.WebProfileListModule
import com.example.githubprofiles.ui.profilelist.ProfileListFragment
import com.example.githubprofiles.ui.profilelist.viewmodel.ProfileListViewModel
import com.example.githubprofiles.ui.profilelist.viewmodel.ProfileListViewModelFactory
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
    fun viewModel(): ProfileListViewModel
    fun viewModelFactory(): ProfileListViewModelFactory
}