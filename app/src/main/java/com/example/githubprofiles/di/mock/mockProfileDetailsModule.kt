package com.example.githubprofiles.di.mock

import com.example.githubprofiles.data.mock.usecase.MockProfileDetailsUsecaseImpl
import com.example.githubprofiles.data.mock.usecase.MockRepoCommonUsecaseImpl
import com.example.githubprofiles.domain.usecase.RepositoryUsecase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MockProfileDetailsModule {

    @Singleton
    @Provides
    fun getMockDetailsCommonUsecase(): RepositoryUsecase.MockProfileDetailsUsecase {
        return MockProfileDetailsUsecaseImpl()
    }

    @Singleton
    @Provides
    fun getMockRepoCommonUsecase(): RepositoryUsecase.MockRepoCommonUsecase {
        return MockRepoCommonUsecaseImpl()
    }
}