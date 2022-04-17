package com.example.githubprofiles.data

import com.example.githubprofiles.domain.ProfileRepo
import com.example.githubprofiles.domain.entities.GitHubProfileDetailsDTO
import com.example.githubprofiles.domain.entities.GitHubProfileListItemDTO
import com.example.githubprofiles.domain.entities.GitHubProfileRepoListItemDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ProfileRetrofitImpl : ProfileRepo {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: GitHubApi = retrofit.create(GitHubApi::class.java)

    override fun getUserProfileListItem(): Single<List<GitHubProfileListItemDTO>> {
        return api.listUsers()
    }

    override fun getUserProfileDetails(userLogin: String?): Single<GitHubProfileDetailsDTO> {
        return api.userDetails(userLogin)
    }

    override fun getUserProfileRepoListItem(userLogin: String?): Single<List<GitHubProfileRepoListItemDTO>> {
        return api.listUserRepos(userLogin)
    }
}