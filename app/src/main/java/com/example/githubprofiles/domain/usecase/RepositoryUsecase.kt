package com.example.githubprofiles.domain.usecase

import com.example.githubprofiles.data.db.entity.DBProfileCommon
import com.example.githubprofiles.data.db.entity.DBProfileDetails
import com.example.githubprofiles.data.db.entity.DBRepoCommon
import com.example.githubprofiles.data.mock.entity.MockProfileCommon
import com.example.githubprofiles.data.mock.entity.MockProfileDetails
import com.example.githubprofiles.data.mock.entity.MockRepoCommon
import com.example.githubprofiles.data.web.entity.WebProfileCommon
import com.example.githubprofiles.data.web.entity.WebProfileDetails
import com.example.githubprofiles.data.web.entity.WebRepoCommon
import io.reactivex.rxjava3.core.Single

interface RepositoryUsecase {

    interface MockProfileCommonUsecase {
        fun receive(): List<MockProfileCommon>
    }

    interface MockProfileDetailsUsecase {
        fun receiveItem(login: String): MockProfileDetails
    }

    interface MockRepoCommonUsecase {
        fun receiveItem(login: String): List<MockRepoCommon>
        fun receive(): List<MockRepoCommon>
    }

    interface WebProfileCommonUsecase {
        fun receive(): Single<List<WebProfileCommon>>
    }

    interface WebProfileDetailsUsecase {
        fun receive(login: String): Single<WebProfileDetails>?
    }

    interface WebRepoCommonUsecase {
        fun receive(login: String): Single<List<WebRepoCommon>>?
        fun receiveClone(): Single<List<WebRepoCommon>>?
    }

    interface DBProfileCommonUsecase {
        fun receive(): List<DBProfileCommon>
    }

    interface DBProfileDetailsUsecase {
        fun receive(login: String): DBProfileDetails
    }

    interface DBRepoCommonUsecase {
        fun receive(login: String): List<DBRepoCommon>
    }
}