package com.example.githubprofiles.ui.profiledetails.viewmodel

import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubprofiles.data.web.entity.WebProfileDetails
import com.example.githubprofiles.data.web.entity.WebRepoCommon
import com.example.githubprofiles.domain.usecase.RepositoryUsecase
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class ProfileDetailsViewModel
@Inject
constructor(
    private val profileDetails: RepositoryUsecase.WebProfileDetailsUsecase,
    private val profileRepos: RepositoryUsecase.WebRepoCommonUsecase
) : ViewModel() {
    private val _profile = MutableLiveData<WebProfileDetails>()
    val profile: LiveData<WebProfileDetails> = _profile

    private val _repos = MutableLiveData<List<WebRepoCommon>>()
    val repos: LiveData<List<WebRepoCommon>> = _repos

    private val _onError = MutableLiveData<Throwable>()
    val onError: LiveData<Throwable> = _onError

    private val _inProgress = MutableLiveData<Boolean>()
    val inProgress: LiveData<Boolean> = _inProgress

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getProfileDetails(userLogin: String) = getProfileDetailsRemote(userLogin)

    fun getProfileRepos(userLogin: String) = getProfileReposRemote(userLogin)

    private fun getProfileDetailsRemote(userLogin: String) {
        profileDetails
            .receive(userLogin)
            ?.subscribeBy(
                onSuccess = {
                    _inProgress.postValue(false)
                    _profile.postValue(it)

                },
                onError = {
                    _onError.postValue(NetworkErrorException("Error loading data!"))
                }
            )?.let {
            compositeDisposable.add(
                it
            )
        }
    }

    private fun getProfileReposRemote(userLogin: String) {
        _inProgress.postValue(true)
        (profileRepos
            .receive(userLogin)
            ?.subscribeBy(
                onSuccess = {
                    _inProgress.postValue(false)
                    _repos.postValue(it)
                },
                onError = {
                    _onError.postValue(NetworkErrorException("Error loading data!"))
                }
            ))?.let {
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