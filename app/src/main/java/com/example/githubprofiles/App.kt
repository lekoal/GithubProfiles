package com.example.githubprofiles

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.example.githubprofiles.data.GitHubApi
import com.example.githubprofiles.data.ProfileRetrofitImpl
import com.example.githubprofiles.domain.ProfileRepo
import com.example.githubprofiles.utils.BasePresenter
import com.example.githubprofiles.utils.PresenterStore
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class App : Application() {
    private val baseUrl = "https://api.github.com/"
    private val jsonConverter by lazy { GsonConverterFactory.create() }
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(jsonConverter)
            .build()
    }
    private val gitHubApi: GitHubApi = retrofit.create(GitHubApi::class.java)

    val gitHubGetUsersData: ProfileRepo by lazy {
        ProfileRetrofitImpl(gitHubApi)
    }

    private val storage: MutableMap<String, BasePresenter> = WeakHashMap()

    val presenterStore by lazy {
        PresenterStore(storage)
    }
}

val Context.app: App
    get() = applicationContext as App

val Fragment.app: App
    get() = requireActivity().app