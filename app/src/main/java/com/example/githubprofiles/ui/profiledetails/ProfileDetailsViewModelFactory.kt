package com.example.githubprofiles.ui.profiledetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubprofiles.domain.usecase.RepositoryUsecase

class ProfileDetailsViewModelFactory(
    private val profileDetails: RepositoryUsecase.WebProfileDetailsUsecase,
    private val profileRepos: RepositoryUsecase.WebRepoCommonUsecase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProfileDetailsViewModel(profileDetails, profileRepos) as T
    }
}