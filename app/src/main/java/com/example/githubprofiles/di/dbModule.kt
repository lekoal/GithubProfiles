package com.example.githubprofiles.di

import android.content.Context
import androidx.room.Room
import com.example.githubprofiles.data.db.DBProfileCommonUsecaseImpl
import com.example.githubprofiles.data.db.DBProfileDetailsUsecaseImpl
import com.example.githubprofiles.data.db.DBRepoCommonUsecaseImpl
import com.example.githubprofiles.data.db.room.dao.ProfileCommonDao
import com.example.githubprofiles.data.db.room.dao.ProfileDetailsDao
import com.example.githubprofiles.data.db.room.dao.RepoCommonDao
import com.example.githubprofiles.data.db.room.database.ProfileCommonDataBase
import com.example.githubprofiles.data.db.room.database.ProfileDetailsDataBase
import com.example.githubprofiles.data.db.room.database.RepoCommonDataBase
import com.example.githubprofiles.domain.usecase.RepositoryUsecase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBDependenciesModule(val context: Context) {

    @Singleton
    @Provides
    fun getProfileCommonDB(): ProfileCommonDataBase {
        return Room.databaseBuilder(
            context,
            ProfileCommonDataBase::class.java,
            "ProfileDetails.db"
        )
            .build()
    }

    @Singleton
    @Provides
    fun getProfileCommonDao(): ProfileCommonDao {
        return getProfileCommonDB().profileCommonDao()
    }

    @Singleton
    @Provides
    fun getDBProfileCommonUsecase(): RepositoryUsecase.DBProfileCommonUsecase {
        return DBProfileCommonUsecaseImpl(getProfileCommonDao())
    }

    @Singleton
    @Provides
    fun getProfileDetailsDB(): ProfileDetailsDataBase {
        return Room.databaseBuilder(
            context,
            ProfileDetailsDataBase::class.java,
            "ProfileDetails.db"
        )
            .build()
    }

    @Singleton
    @Provides
    fun getProfileDetailsDao(): ProfileDetailsDao {
        return getProfileDetailsDB().profileDetailsDao()
    }

    @Singleton
    @Provides
    fun getDBProfileDetailsUsecase(): RepositoryUsecase.DBProfileDetailsUsecase {
        return DBProfileDetailsUsecaseImpl(getProfileDetailsDao())
    }

    @Singleton
    @Provides
    fun getRepoCommonDB(): RepoCommonDataBase {
        return Room.databaseBuilder(
            context,
            RepoCommonDataBase::class.java,
            "RepoCommon.db"
        )
            .build()
    }

    @Singleton
    @Provides
    fun getRepoCommonDao(): RepoCommonDao {
        return getRepoCommonDB().repoCommonDao()
    }

    @Singleton
    @Provides
    fun getDBRepoCommonUsecase(): RepositoryUsecase.DBRepoCommonUsecase {
        return DBRepoCommonUsecaseImpl(getRepoCommonDao())
    }

}