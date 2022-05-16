package com.example.githubprofiles.di

import com.example.githubprofiles.utils.PresenterStore
import dagger.Module
import dagger.Provides
import java.util.*

@Module
class StorageModule {

    @Provides
    fun getPresenterStore(): PresenterStore {
        return PresenterStore(WeakHashMap())
    }
}