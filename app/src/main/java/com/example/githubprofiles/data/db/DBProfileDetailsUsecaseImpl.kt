package com.example.githubprofiles.data.db

import com.example.githubprofiles.data.db.entity.DBProfileDetails
import com.example.githubprofiles.data.db.room.dao.ProfileDetailsDao
import com.example.githubprofiles.domain.usecase.RepositoryUsecase

class DBProfileDetailsUsecaseImpl(
    private val localDataSource: ProfileDetailsDao
) : RepositoryUsecase.DBProfileDetailsUsecase {
    override fun receive(login: String): DBProfileDetails {
        return localDataSource.getProfileDetails(login)
    }
}