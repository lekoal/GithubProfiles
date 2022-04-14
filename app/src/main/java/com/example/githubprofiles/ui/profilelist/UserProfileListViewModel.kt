package com.example.githubprofiles.ui.profilelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubprofiles.data.UserProfileRepoImpl
import com.example.githubprofiles.repo.UserProfileRepo
import com.example.githubprofiles.ui.AppState

class UserProfileListViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: UserProfileRepo = UserProfileRepoImpl
) : ViewModel() {

    fun getLiveData() = liveData

    private fun getUserProfileList() {
        liveData.value = AppState.Loading
        liveData.postValue(
            AppState.LoadingUserProfileListSuccess(
                repositoryImpl.getUserProfileListItem()
            )
        )
    }
}