package com.example.githubprofiles.ui.profilelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubprofiles.data.UserProfileRepoImpl
import com.example.githubprofiles.repo.UserProfileRepo
import com.example.githubprofiles.ui.AppState
import java.lang.Thread.sleep

class UserProfileListViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repositoryImpl: UserProfileRepo = UserProfileRepoImpl
) : ViewModel() {

    fun getLiveData() = liveData

    fun getUserProfileList() = getUserProfileListLocal()

    private fun getUserProfileListLocal() {
        liveData.postValue(AppState.Loading)
        Thread {
            sleep(2000)
            liveData.postValue(
                AppState.LoadingUserProfileListSuccess(
                    repositoryImpl.getUserProfileListItem()
                )
            )
        }.start()
    }
}