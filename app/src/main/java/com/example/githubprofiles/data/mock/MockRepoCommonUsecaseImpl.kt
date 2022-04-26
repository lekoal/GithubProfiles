package com.example.githubprofiles.data.mock

import com.example.githubprofiles.domain.usecase.RepositoryUsecase

class MockRepoCommonUsecaseImpl : RepositoryUsecase.MockRepoCommonUsecase {
    private val repos: List<MockRepoCommon> = listOf(
        MockRepoCommon(
            "user1",
            "Repository 1",
            "02.02.2022"
        ),
        MockRepoCommon(
            "user1",
            "Repository 2",
            "03.02.2022"
        ),
        MockRepoCommon(
            "user1",
            "Repository 3",
            "04.02.2022"
        ),
        MockRepoCommon(
            "user2",
            "Repository 1",
            "03.02.2022"
        ),
        MockRepoCommon(
            "user2",
            "Repository 2",
            "04.02.2022"
        ),
        MockRepoCommon(
            "user2",
            "Repository 3",
            "05.02.2022"
        ),
        MockRepoCommon(
            "user3",
            "Repository 1",
            "04.02.2022"
        ),
        MockRepoCommon(
            "user3",
            "Repository 2",
            "05.02.2022"
        ),
        MockRepoCommon(
            "user3",
            "Repository 3",
            "06.02.2022"
        ),
        MockRepoCommon(
            "user4",
            "Repository 1",
            "05.02.2022"
        ),
        MockRepoCommon(
            "user4",
            "Repository 2",
            "06.02.2022"
        ),
        MockRepoCommon(
            "user4",
            "Repository 3",
            "07.02.2022"
        )
    )
    private val result: MutableList<MockRepoCommon> = mutableListOf()
    override fun receive(login: String): List<MockRepoCommon> {
        repos.forEach { mockRepoCommon ->
            if (mockRepoCommon.login == login) {
                result.add(mockRepoCommon)
            }
        }
        return result
    }
}