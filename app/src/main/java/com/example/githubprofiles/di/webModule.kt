package com.example.githubprofiles.di

import com.example.githubprofiles.data.GitHubApi
import com.example.githubprofiles.data.web.WebProfileCommonUsecaseImpl
import com.example.githubprofiles.data.web.WebProfileDetailsUsecaseImpl
import com.example.githubprofiles.data.web.WebRepoCommonUsecaseImpl
import com.example.githubprofiles.domain.usecase.RepositoryUsecase
import com.example.githubprofiles.ui.profiledetails.ProfileDetailsRecyclerAdapter
import com.example.githubprofiles.ui.profiledetails.ProfileDetailsViewModel
import com.example.githubprofiles.ui.profilelist.ProfileListRecyclerAdapter
import com.example.githubprofiles.ui.profilelist.ProfileListViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

//val webModule = module {
//
//    viewModel { ProfileListViewModel(get()) }
//    viewModel { ProfileDetailsViewModel(get(), get()) }
//    single { ProfileListRecyclerAdapter() }
//    single { ProfileDetailsRecyclerAdapter() }
//}

@Module
class WebDependenciesModule() {

    @Singleton
    @Provides
    fun getWebProfileCommonUsecase(gitHubApi: GitHubApi): RepositoryUsecase.WebProfileCommonUsecase {
        return WebProfileCommonUsecaseImpl(gitHubApi)
    }

    @Singleton
    @Provides
    fun getWebProfileDetailsUsecase(gitHubApi: GitHubApi): RepositoryUsecase.WebProfileDetailsUsecase {
        return WebProfileDetailsUsecaseImpl(gitHubApi)
    }

    @Singleton
    @Provides
    fun getWebRepoCommonUsecase(gitHubApi: GitHubApi): RepositoryUsecase.WebRepoCommonUsecase {
        return WebRepoCommonUsecaseImpl(gitHubApi)
    }

    @Singleton
    @Provides
    fun getGitHubApi(retrofit: Retrofit): GitHubApi {
        return retrofit.create(GitHubApi::class.java)
    }

    @Provides
    fun getConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    @Named("api_url")
    fun getBaseUrl(): String {
        return "https://api.github.com/"
    }

    @Singleton
    @Provides
    fun getRetrofit(baseUrl: String, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(converterFactory)
            .build()
    }
}