package com.example.githubprofiles.ui

import com.example.githubprofiles.domain.entities.GitHubProfileDetailsDTO
import com.example.githubprofiles.domain.entities.GitHubProfileListItemDTO
import com.example.githubprofiles.domain.entities.GitHubProfileRepoListItemDTO

sealed class AppState {
    data class LoadingUserProfileListSuccess(
        val getProfileListData: List<GitHubProfileListItemDTO>?
    ) : AppState()

    data class LoadingUserProfileRepoSuccess(
        val getProfileRepoList: List<GitHubProfileRepoListItemDTO>?,
        val getProfileDetailsData: GitHubProfileDetailsDTO?
    ) : AppState()

    data class LoadingError(val loadingError: Throwable) : AppState()

    object Loading : AppState()
}