package com.example.githubprofiles.di.web

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubprofiles.di.scope.AppScope
import com.example.githubprofiles.ui.profilelist.viewmodel.ProfileListViewModel
import com.example.githubprofiles.ui.profilelist.viewmodel.ProfileListViewModelFactory
import com.example.githubprofiles.ui.profilelist.viewmodel.ProfileListViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class WebProfileListViewModelModule {
    @AppScope
    @Binds
    @IntoMap
    @ProfileListViewModelKey(ProfileListViewModel::class)
    abstract fun bindViewModel(
        profileListViewModel: ProfileListViewModel
    ): ViewModel

    @AppScope
    @Binds
    abstract fun bindViewModelFactory(
        vmFactory: ProfileListViewModelFactory
    ): ViewModelProvider.Factory
}