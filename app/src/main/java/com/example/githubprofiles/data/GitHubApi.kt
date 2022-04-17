package com.example.githubprofiles.data

import com.example.githubprofiles.domain.entities.GitHubProfileDetailsDTO
import com.example.githubprofiles.domain.entities.GitHubProfileListItemDTO
import com.example.githubprofiles.domain.entities.GitHubProfileRepoListItemDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users")
    fun listProfiles(): Single<List<GitHubProfileListItemDTO>>

    @GET("users/{user}")
    fun profileDetails(@Path("user") user: String?): Single<GitHubProfileDetailsDTO>

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Single<List<GitHubProfileRepoListItemDTO>>
}