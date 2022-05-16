package com.example.githubprofiles.data.web.usecase

import com.example.githubprofiles.data.GitHubApi
import com.example.githubprofiles.data.web.entity.WebRepoCommon
import com.example.githubprofiles.domain.usecase.RepositoryUsecase
import io.reactivex.rxjava3.core.Single

class WebRepoCommonUsecaseImpl(
    private val api: GitHubApi
) : RepositoryUsecase.WebRepoCommonUsecase {
    override fun receive(login: String): Single<List<WebRepoCommon>> {
        return api.listRepos(login)
    }
}