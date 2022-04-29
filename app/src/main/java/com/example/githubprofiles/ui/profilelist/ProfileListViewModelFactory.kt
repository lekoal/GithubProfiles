package com.example.githubprofiles.ui.profilelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubprofiles.domain.usecase.RepositoryUsecase

class ProfileListViewModelFactory(private val repo: RepositoryUsecase.WebProfileCommonUsecase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileListViewModel(repo) as T
    }
}