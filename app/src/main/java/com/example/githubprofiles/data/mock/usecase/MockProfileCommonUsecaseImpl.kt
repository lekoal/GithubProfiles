package com.example.githubprofiles.data.mock.usecase

import com.example.githubprofiles.data.mock.entity.MockProfileCommon
import com.example.githubprofiles.domain.usecase.RepositoryUsecase

class MockProfileCommonUsecaseImpl : RepositoryUsecase.MockProfileCommonUsecase {
    override fun receive(): List<MockProfileCommon> {
        return listOf(
            MockProfileCommon(
                "user1",
                ""
            ),
            MockProfileCommon(
                "user2",
                ""
            ),
            MockProfileCommon(
                "user3",
                ""
            ),
            MockProfileCommon(
                "user4",
                ""
            )
        )
    }
}