package com.example.githubprofiles.di

import androidx.room.Room
import com.example.githubprofiles.data.GitHubApi
import com.example.githubprofiles.data.db.room.database.ProfileCommonDataBase
import com.example.githubprofiles.data.db.room.database.ProfileDetailsDataBase
import com.example.githubprofiles.data.db.room.database.RepoCommonDataBase
import com.example.githubprofiles.data.mock.MockProfileCommonUsecaseImpl
import com.example.githubprofiles.data.mock.MockProfileDetailsUsecaseImpl
import com.example.githubprofiles.data.mock.MockRepoCommonUsecaseImpl
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
    single<RepositoryUsecase.MockProfileCommonUsecase> { MockProfileCommonUsecaseImpl() }
    single<RepositoryUsecase.MockProfileDetailsUsecase> { MockProfileDetailsUsecaseImpl() }
    single<RepositoryUsecase.MockRepoCommonUsecase> { MockRepoCommonUsecaseImpl() }

    // DB
    single {
        Room.databaseBuilder(get(), ProfileCommonDataBase::class.java, "ProfileCommon.db")
            .build()
    }
    single { get<ProfileCommonDataBase>().profileCommonDao() }
    single {
        Room.databaseBuilder(get(), ProfileDetailsDataBase::class.java, "ProfileDetails.db")
            .build()
    }
    single { get<ProfileDetailsDataBase>().profileDetailsDao() }
    single {
        Room.databaseBuilder(get(), RepoCommonDataBase::class.java, "RepoCommon.db")
            .build()
    }
    single { get<RepoCommonDataBase>().repoCommonDao() }

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