package com.example.githubprofiles.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubprofiles.domain.ProfileRepo
import com.example.githubprofiles.ui.profilelist.ProfileListViewModel

class ProfileViewModelFactory(private val repo: ProfileRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileListViewModel(repo) as T
    }
}