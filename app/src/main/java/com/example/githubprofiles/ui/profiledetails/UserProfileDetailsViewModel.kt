package com.example.githubprofiles.ui.profiledetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubprofiles.data.UserProfileRepoImpl
import com.example.githubprofiles.repo.UserProfileRepo
import com.example.githubprofiles.ui.AppState

class UserProfileDetailsViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: UserProfileRepo = UserProfileRepoImpl
) : ViewModel() {

    fun getLiveData() = liveData

    private fun getUserProfileDetails(userLogin: String) {
        liveData.value = AppState.Loading
        liveData.postValue(
            AppState.LoadingUserProfileDetailsSuccess(
                repositoryImpl.getUserProfileDetails(userLogin),
                repositoryImpl.getUserProfileRepoListItem(userLogin)
            )
        )
    }
}