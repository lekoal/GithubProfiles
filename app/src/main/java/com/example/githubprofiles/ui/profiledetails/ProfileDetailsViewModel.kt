package com.example.githubprofiles.ui.profiledetails

import android.accounts.NetworkErrorException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubprofiles.domain.ProfileRepo
import com.example.githubprofiles.domain.entities.GitHubProfileDetailsDTO
import com.example.githubprofiles.domain.entities.GitHubProfileRepoListItemDTO
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy

class ProfileDetailsViewModel(private val profileRepo: ProfileRepo) : ViewModel() {
    private val _profile = MutableLiveData<GitHubProfileDetailsDTO>()
    val profile: LiveData<GitHubProfileDetailsDTO> = _profile

    private val _repos = MutableLiveData<List<GitHubProfileRepoListItemDTO>>()
    val repos: LiveData<List<GitHubProfileRepoListItemDTO>> = _repos

    private val _onError = MutableLiveData<Throwable>()
    val onError: LiveData<Throwable> = _onError

    private val _inProgress = MutableLiveData<Boolean>()
    val inProgress: LiveData<Boolean> = _inProgress

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getProfileDetails(userLogin: String) = getProfileDetailsRemote(userLogin)

    fun getProfileRepos(userLogin: String) = getProfileReposRemote(userLogin)

    private fun getProfileDetailsRemote(userLogin: String) {
        compositeDisposable.add(
            profileRepo
                .getProfileDetails(userLogin)
                .subscribeBy(
                    onSuccess = {
                        _inProgress.postValue(false)
                        _profile.postValue(it)

                    },
                    onError = {
                        _onError.postValue(NetworkErrorException("Error loading data!"))
                    }
                )
        )
    }

    private fun getProfileReposRemote(userLogin: String) {
        _inProgress.postValue(true)
        compositeDisposable.add(
            profileRepo
                .getProfileRepoListItem(userLogin)
                .subscribeBy(
                    onSuccess = {
                        _inProgress.postValue(false)
                        _repos.postValue(it)
                    },
                    onError = {
                        _onError.postValue(NetworkErrorException("Error loading data!"))
                    }
                )
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}