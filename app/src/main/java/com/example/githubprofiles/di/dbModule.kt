package com.example.githubprofiles.di

import androidx.room.Room
import com.example.githubprofiles.data.db.DBProfileCommonUsecaseImpl
import com.example.githubprofiles.data.db.DBProfileDetailsUsecaseImpl
import com.example.githubprofiles.data.db.DBRepoCommonUsecaseImpl
import com.example.githubprofiles.data.db.room.database.ProfileCommonDataBase
import com.example.githubprofiles.data.db.room.database.ProfileDetailsDataBase
import com.example.githubprofiles.data.db.room.database.RepoCommonDataBase
import com.example.githubprofiles.domain.usecase.RepositoryUsecase
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(get(), ProfileCommonDataBase::class.java, "ProfileCommon.db")
            .build()
    }
    single { get<ProfileCommonDataBase>().profileCommonDao() }
    single<RepositoryUsecase.DBProfileCommonUsecase> { DBProfileCommonUsecaseImpl(get()) }
    single {
        Room.databaseBuilder(get(), ProfileDetailsDataBase::class.java, "ProfileDetails.db")
            .build()
    }
    single { get<ProfileDetailsDataBase>().profileDetailsDao() }
    single<RepositoryUsecase.DBProfileDetailsUsecase> { DBProfileDetailsUsecaseImpl(get()) }
    single {
        Room.databaseBuilder(get(), RepoCommonDataBase::class.java, "RepoCommon.db")
            .build()
    }
    single { get<RepoCommonDataBase>().repoCommonDao() }
    single<RepositoryUsecase.DBRepoCommonUsecase> { DBRepoCommonUsecaseImpl(get()) }
}