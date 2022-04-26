package com.example.githubprofiles.data.web

import com.example.githubprofiles.data.GitHubApi
import com.example.githubprofiles.domain.usecase.RepositoryUsecase
import io.reactivex.rxjava3.core.Single

class WebProfileCommonUsecaseImpl(
    private val api: GitHubApi
) : RepositoryUsecase.WebProfileCommonUsecase {
    override fun receive(): Single<List<WebProfileCommon>> {
        return api.listProfiles()
    }
}