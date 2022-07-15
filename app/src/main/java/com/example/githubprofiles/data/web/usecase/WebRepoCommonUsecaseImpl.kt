package com.example.githubprofiles.data.web.usecase

import com.example.githubprofiles.data.GitHubApi
import com.example.githubprofiles.data.web.entity.WebRepoCommon
import com.example.githubprofiles.domain.usecase.RepositoryUsecase
import io.reactivex.rxjava3.core.Single

class WebRepoCommonUsecaseImpl(
    private val api: GitHubApi
) : RepositoryUsecase.WebRepoCommonUsecase {
    private var originData: Single<List<WebRepoCommon>>? = null
    private var cloneData: Single<List<WebRepoCommon>>? = null
    override fun receive(login: String): Single<List<WebRepoCommon>>? {
        originData = api.listRepos(login)
        cloneData = originData
        return if (login != "") {
            originData
        } else {
            null
        }
    }

    override fun receiveClone(): Single<List<WebRepoCommon>>? {
        return cloneData
    }
}