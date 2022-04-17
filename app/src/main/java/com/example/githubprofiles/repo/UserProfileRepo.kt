package com.example.githubprofiles.repo

import com.example.githubprofiles.repo.datasource.GitHubUserProfileDetailsDTO
import com.example.githubprofiles.repo.datasource.GitHubUserProfileListItemDTO
import com.example.githubprofiles.repo.datasource.GitHubUserProfileRepoListItemDTO

interface UserProfileRepo {
    fun getUserProfileListItem(): List<GitHubUserProfileListItemDTO>?
    fun getUserProfileDetails(userLogin: String?): GitHubUserProfileDetailsDTO?
    fun getUserProfileRepoListItem(userLogin: String?): List<GitHubUserProfileRepoListItemDTO>?
}