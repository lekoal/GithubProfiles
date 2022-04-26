package com.example.githubprofiles.di

import com.example.githubprofiles.data.mock.MockProfileCommonUsecaseImpl
import com.example.githubprofiles.data.mock.MockProfileDetailsUsecaseImpl
import com.example.githubprofiles.data.mock.MockRepoCommonUsecaseImpl
import com.example.githubprofiles.domain.usecase.RepositoryUsecase
import org.koin.dsl.module

val mockModule = module {
    single<RepositoryUsecase.MockProfileCommonUsecase> { MockProfileCommonUsecaseImpl() }
    single<RepositoryUsecase.MockProfileDetailsUsecase> { MockProfileDetailsUsecaseImpl() }
    single<RepositoryUsecase.MockRepoCommonUsecase> { MockRepoCommonUsecaseImpl() }
}