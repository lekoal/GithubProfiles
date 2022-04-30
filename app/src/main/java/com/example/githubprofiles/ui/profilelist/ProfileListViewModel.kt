package com.example.githubprofiles.ui.profilelist

import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubprofiles.data.web.entity.WebProfileCommon
import com.example.githubprofiles.domain.usecase.RepositoryUsecase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class ProfileListViewModel(private val profileRepo: RepositoryUsecase.WebProfileCommonUsecase) :
    ViewModel() {
    private val _profiles = MutableLiveData<List<WebProfileCommon>>()
    val profiles: LiveData<List<WebProfileCommon>> = _profiles

    private val _inProgress = MutableLiveData<Boolean>()
    val inProgress: LiveData<Boolean> = _inProgress

    private val _onError = MutableLiveData<Throwable>()
    val onError: LiveData<Throwable> = _onError

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getUserProfileList() = getUserProfileListRemote()

    private fun getUserProfileListRemote() {
        _inProgress.postValue(true)
        compositeDisposable.add(
            profileRepo
                .receive()
                .subscribeBy(
                    onSuccess = {
                        _inProgress.postValue(false)
                        _profiles.postValue(it)
                    },
                    onError = {
                        _inProgress.postValue(false)
                        _onError.postValue(NetworkErrorException("Error loading data!"))
                    })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}