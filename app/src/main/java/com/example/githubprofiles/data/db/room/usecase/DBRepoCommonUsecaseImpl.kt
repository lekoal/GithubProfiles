package com.example.githubprofiles.data.db.room.usecase

import com.example.githubprofiles.data.db.entity.DBRepoCommon
import com.example.githubprofiles.data.db.room.dao.RepoCommonDao
import com.example.githubprofiles.domain.usecase.RepositoryUsecase

class DBRepoCommonUsecaseImpl(
    private val localDataSource: RepoCommonDao
) : RepositoryUsecase.DBRepoCommonUsecase {
    override fun receive(login: String): List<DBRepoCommon> {
        return localDataSource.getRepoCommon(login)
    }
}