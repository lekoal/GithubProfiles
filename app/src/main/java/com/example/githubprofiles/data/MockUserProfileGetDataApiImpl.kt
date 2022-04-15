package com.example.githubprofiles.data

import com.example.githubprofiles.repo.UserProfileGetDataApi

class MockUserProfileGetDataApiImpl : UserProfileGetDataApi{
    override fun getUserProfileListItem(): Boolean {
        if (!UserProfileRepoImpl.getUserProfileListItem().isNullOrEmpty()) {
            return true
        }
        return false
    }

    override fun getUserProfileDetails(userLogin: String): Boolean {
        if (UserProfileRepoImpl.getUserProfileDetails(userLogin) != null) {
            return true
        }
        return false
    }

    override fun getUserProfileRepoListItem(userLogin: String): Boolean {
        if (!UserProfileRepoImpl.getUserProfileRepoListItem(userLogin).isNullOrEmpty()) {
            return true
        }
        return false
    }

}