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
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single(named("api_url")) { "https://api.github.com/" }

    // Web
    single<RepositoryUsecase.WebProfileCommonUsecase> { WebProfileCommonUsecaseImpl(get()) }
    single<RepositoryUsecase.WebProfileDetailsUsecase> { WebProfileDetailsUsecaseImpl(get()) }
    single<RepositoryUsecase.WebRepoCommonUsecase> { WebRepoCommonUsecaseImpl(get()) }

    // Mock


    // DB

    single<GitHubApi> { get<Retrofit>().create(GitHubApi::class.java) }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(get<String>(named("api_url")))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(get())
            .build()
    }
    factory<Converter.Factory> { GsonConverterFactory.create() }
    viewModel { ProfileListViewModel(get()) }
    viewModel { ProfileDetailsViewModel(get(), get()) }
    single { ProfileListRecyclerAdapter() }
    single { ProfileDetailsRecyclerAdapter() }
}