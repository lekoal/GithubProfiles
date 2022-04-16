package com.example.githubprofiles.ui.profilelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubprofiles.domain.ProfileRepo

class ProfileViewModelFactory(private val repo: ProfileRepo?) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileListViewModel(repo) as T
    }
}