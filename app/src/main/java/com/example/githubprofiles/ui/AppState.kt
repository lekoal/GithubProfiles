package com.example.githubprofiles.ui

import com.example.githubprofiles.repo.datasource.GitHubUserProfileDTO
import com.example.githubprofiles.repo.datasource.GitHubUserProfileListItemDTO
import com.example.githubprofiles.repo.datasource.GitHubUserProfileRepoListItemDTO

sealed class AppState {
    data class LoadingSuccess(
        val getUserProfileListData: List<GitHubUserProfileListItemDTO>?,
        val getUserProfileDetailsData: GitHubUserProfileDTO?,
        val getUserProfileRepoList: List<GitHubUserProfileRepoListItemDTO>?
    ) : AppState()

    data class LoadingError(val loadingError: Throwable) : AppState()

    object Loading : AppState()
}