package com.example.githubprofiles.data.web.usecase

import com.example.githubprofiles.data.GitHubApi
import com.example.githubprofiles.data.web.entity.WebProfileDetails
import com.example.githubprofiles.domain.usecase.RepositoryUsecase
import io.reactivex.rxjava3.core.Single

class WebProfileDetailsUsecaseImpl(
    private val api: GitHubApi
) : RepositoryUsecase.WebProfileDetailsUsecase {
    override fun receive(login: String): Single<WebProfileDetails>? {
        return if (login != "") {
            api.profileDetails(login)
        } else {
            null
        }
    }
}