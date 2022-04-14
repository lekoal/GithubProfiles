package com.example.githubprofiles.repo

interface UserProfileGetDataApi {
    fun getUserProfileListItem(): Boolean
    fun getUserProfileDetails(userLogin: String): Boolean
    fun getUserProfileRepoListItem(userLogin: String): Boolean
}