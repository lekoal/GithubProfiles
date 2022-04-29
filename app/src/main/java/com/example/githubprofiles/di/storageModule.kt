package com.example.githubprofiles.di

import com.example.githubprofiles.utils.BasePresenter
import com.example.githubprofiles.utils.PresenterStore
import dagger.Module
import dagger.Provides
import java.util.*

@Module
class StorageModule {

    @Provides
    fun getMutableMap(): MutableMap<String, BasePresenter> {
        return WeakHashMap()
    }

    @Provides
    fun getPresenterStore(presenterMap: MutableMap<String, BasePresenter>): PresenterStore {
        return PresenterStore(presenterMap)
    }
}