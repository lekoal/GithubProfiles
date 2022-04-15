package com.example.githubprofiles.ui

import com.example.githubprofiles.repo.datasource.GitHubUserProfileDetailsDTO
import com.example.githubprofiles.repo.datasource.GitHubUserProfileListItemDTO
import com.example.githubprofiles.repo.datasource.GitHubUserProfileRepoListItemDTO

sealed class AppState {
    data class LoadingUserProfileListSuccess(
        val getUserProfileListData: List<GitHubUserProfileListItemDTO>?
    ) : AppState()

    data class LoadingUserProfileRepoSuccess(
        val getUserProfileRepoList: List<GitHubUserProfileRepoListItemDTO>?,
        val getUserProfileDetailsData: GitHubUserProfileDetailsDTO?
    ) : AppState()

    data class LoadingError(val loadingError: Throwable) : AppState()

    object Loading : AppState()
}