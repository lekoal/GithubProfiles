package com.example.githubprofiles.di.db

import android.content.Context
import androidx.room.Room
import com.example.githubprofiles.data.db.DBProfileCommonUsecaseImpl
import com.example.githubprofiles.data.db.room.dao.ProfileCommonDao
import com.example.githubprofiles.data.db.room.database.ProfileCommonDataBase
import com.example.githubprofiles.domain.usecase.RepositoryUsecase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DBProfileListModule(val context: Context) {

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
    fun getProfileCommonDao(
        profileCommonDB: ProfileCommonDataBase
    ): ProfileCommonDao {
        return profileCommonDB.profileCommonDao()
    }

    @Singleton
    @Provides
    fun getDBProfileCommonUsecase(
        profileCommonDao: ProfileCommonDao
    ): RepositoryUsecase.DBProfileCommonUsecase {
        return DBProfileCommonUsecaseImpl(profileCommonDao)
    }
}