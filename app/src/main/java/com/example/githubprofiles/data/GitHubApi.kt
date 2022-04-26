package com.example.githubprofiles.data

import com.example.githubprofiles.data.web.WebProfileDetails
import com.example.githubprofiles.data.web.WebProfileCommon
import com.example.githubprofiles.data.web.WebRepoCommon
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users")
    fun listProfiles(): Single<List<WebProfileCommon>>

    @GET("users/{user}")
    fun profileDetails(@Path("user") user: String?): Single<WebProfileDetails>

    @GET("users/{user}/repos")
    fun listRepos(@Path("user") user: String?): Single<List<WebRepoCommon>>
}