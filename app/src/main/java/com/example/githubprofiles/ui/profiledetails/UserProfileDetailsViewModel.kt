package com.example.githubprofiles.ui.profiledetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubprofiles.data.UserProfileRepoImpl
import com.example.githubprofiles.repo.UserProfileRepo
import com.example.githubprofiles.ui.AppState
import java.lang.Thread.sleep

class UserProfileDetailsViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: UserProfileRepo = UserProfileRepoImpl,
) : ViewModel() {

    fun getLiveData() = liveData

    fun getUserProfileDetails(userLogin: String?) = getUserProfileDetailsLocal(userLogin)

    private fun getUserProfileDetailsLocal(userLogin: String?) {
        liveData.postValue(AppState.Loading)
        Thread {
            sleep(2000)
            liveData.postValue(
                AppState.LoadingUserProfileRepoSuccess(
                    repositoryImpl.getUserProfileRepoListItem(userLogin),
                    repositoryImpl.getUserProfileDetails(userLogin)
                )
            )
        }.start()
    }
}