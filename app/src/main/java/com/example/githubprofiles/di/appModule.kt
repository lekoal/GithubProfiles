package com.example.githubprofiles.di

import com.example.githubprofiles.data.GitHubApi
import com.example.githubprofiles.data.ProfileRetrofitImpl
import com.example.githubprofiles.domain.ProfileRepo
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
    single<ProfileRepo> { ProfileRetrofitImpl(get()) }
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
    viewModel { ProfileDetailsViewModel(get()) }
    single { ProfileListRecyclerAdapter() }
    single { ProfileDetailsRecyclerAdapter() }
}