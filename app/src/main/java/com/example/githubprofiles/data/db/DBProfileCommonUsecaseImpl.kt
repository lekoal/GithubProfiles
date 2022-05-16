package com.example.githubprofiles.data.db

import com.example.githubprofiles.data.db.entity.DBProfileCommon
import com.example.githubprofiles.data.db.room.dao.ProfileCommonDao
import com.example.githubprofiles.domain.usecase.RepositoryUsecase

class DBProfileCommonUsecaseImpl(
    private val localDataSource: ProfileCommonDao
) : RepositoryUsecase.DBProfileCommonUsecase {
    override fun receive(): List<DBProfileCommon> {
        return localDataSource.getAllProfiles()
    }
}