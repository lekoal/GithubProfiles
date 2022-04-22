package com.example.githubprofiles.domain

import com.example.githubprofiles.domain.entities.GitHubProfileDetailsDTO
import com.example.githubprofiles.domain.entities.GitHubProfileListItemDTO
import com.example.githubprofiles.domain.entities.GitHubProfileRepoListItemDTO
import io.reactivex.rxjava3.core.Single

interface ProfileRepo {
    fun getProfileListItem(): Single<List<GitHubProfileListItemDTO>>
    fun getProfileDetails(userLogin: String?): Single<GitHubProfileDetailsDTO>
    fun getProfileRepoListItem(userLogin: String?): Single<List<GitHubProfileRepoListItemDTO>>
}