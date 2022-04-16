package com.example.githubprofiles.ui.profilelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubprofiles.domain.ProfileRepo
import com.example.githubprofiles.domain.entities.GitHubProfileListItemDTO
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class ProfileListViewModel(private val profileRepo: ProfileRepo?) : ViewModel() {
    private val _profiles = MutableLiveData<List<GitHubProfileListItemDTO>>()
    val profiles: LiveData<List<GitHubProfileListItemDTO>> = _profiles

    private val _inProgress = MutableLiveData<Boolean>()
    val inProgress: LiveData<Boolean> = _inProgress

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getUserProfileList() = getUserProfileListRemote()

    private fun getUserProfileListRemote() {
        _inProgress.postValue(true)
        profileRepo
            ?.getUserProfileListItem()
            ?.subscribeBy {
                _inProgress.postValue(false)
                _profiles.postValue(it)
            }?.let {
                compositeDisposable.add(
                    it
                )
            }
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}