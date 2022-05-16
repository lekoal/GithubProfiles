package com.example.githubprofiles.data.web.usecase

import com.example.githubprofiles.data.GitHubApi
import com.example.githubprofiles.data.web.entity.WebProfileCommon
import com.example.githubprofiles.domain.usecase.RepositoryUsecase
import io.reactivex.rxjava3.core.Single

class WebProfileCommonUsecaseImpl(
    private val api: GitHubApi
) : RepositoryUsecase.WebProfileCommonUsecase {
    override fun receive(): Single<List<WebProfileCommon>> {
        return api.listProfiles()
    }
}