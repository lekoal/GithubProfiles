package com.example.githubprofiles.di.mock

import com.example.githubprofiles.data.mock.usecase.MockProfileCommonUsecaseImpl
import com.example.githubprofiles.domain.usecase.RepositoryUsecase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MockProfileListModule {

    @Singleton
    @Provides
    fun getMockProfileCommonUsecase(): RepositoryUsecase.MockProfileCommonUsecase {
        return MockProfileCommonUsecaseImpl()
    }
}