package com.example.githubprofiles.data

import com.example.githubprofiles.domain.ProfileRepo
import com.example.githubprofiles.domain.entities.GitHubProfileDetailsDTO
import com.example.githubprofiles.domain.entities.GitHubProfileListItemDTO
import com.example.githubprofiles.domain.entities.GitHubProfileRepoListItemDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit

class ProfileRetrofitImpl(
    private val api: GitHubApi
) : ProfileRepo {
    override fun getProfileListItem():
            Single<List<GitHubProfileListItemDTO>> {
        return api.listProfiles()
    }

    override fun getProfileDetails(userLogin: String?):
            Single<GitHubProfileDetailsDTO> {
        return api.profileDetails(userLogin)
    }

    override fun getProfileRepoListItem(userLogin: String?):
            Single<List<GitHubProfileRepoListItemDTO>> {
        return api.listRepos(userLogin)
    }
}