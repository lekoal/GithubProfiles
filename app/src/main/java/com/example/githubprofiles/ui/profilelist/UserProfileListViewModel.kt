package com.example.githubprofiles.ui.profilelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubprofiles.repo.UserProfileRepo
import com.example.githubprofiles.ui.AppState

class UserProfileListViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: UserProfileRepo = UserProfileRepoImpl()
) : ViewModel() {

    fun getLiveData() = liveData


}