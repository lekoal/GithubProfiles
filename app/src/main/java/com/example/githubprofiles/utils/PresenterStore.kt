package com.example.githubprofiles.utils

class PresenterStore(
    private val storage: MutableMap<String, BasePresenter>
) {
    fun savePresenter(presenter: BasePresenter) {
        storage[presenter.id] = presenter
    }

    fun getPresenter(id: String): BasePresenter? {
        return storage[id]
    }
}

interface BasePresenter {
    val id: String
}