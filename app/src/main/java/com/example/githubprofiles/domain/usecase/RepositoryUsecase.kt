package com.example.githubprofiles.domain.usecase

import com.example.githubprofiles.data.mock.MockProfileCommon
import com.example.githubprofiles.data.mock.MockProfileDetails
import com.example.githubprofiles.data.mock.MockRepoCommon
import com.example.githubprofiles.data.web.WebProfileDetails
import com.example.githubprofiles.data.web.WebProfileCommon
import com.example.githubprofiles.data.web.WebRepoCommon
import io.reactivex.rxjava3.core.Single

interface RepositoryUsecase {

    interface MockProfileCommonUsecase {
        fun receive(login: String): List<MockProfileCommon>
    }

    interface MockProfileDetailsUsecase {
        fun receive(login: String): MockProfileDetails
    }

    interface MockRepoCommonUsecase {
        fun receive(login: String): List<MockRepoCommon>
    }

    interface WebProfileCommonUsecase {
        fun receive(): Single<List<WebProfileCommon>>
    }

    interface WebProfileDetailsUsecase {
        fun receive(login: String): Single<WebProfileDetails>
    }

    interface WebRepoCommonUsecase {
        fun receive(login: String): Single<List<WebRepoCommon>>
    }
}