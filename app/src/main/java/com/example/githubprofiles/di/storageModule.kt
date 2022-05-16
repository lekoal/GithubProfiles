package com.example.githubprofiles.di

import com.example.githubprofiles.utils.BasePresenter
import com.example.githubprofiles.utils.PresenterStore
import org.koin.dsl.module
import java.util.*

val storageModule = module {

    single { PresenterStore(get()) }
    single<MutableMap<String, BasePresenter>> { WeakHashMap() }
}