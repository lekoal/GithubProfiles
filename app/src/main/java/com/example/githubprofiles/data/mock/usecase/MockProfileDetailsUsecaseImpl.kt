package com.example.githubprofiles.data.mock.usecase

import com.example.githubprofiles.data.mock.entity.MockProfileDetails
import com.example.githubprofiles.domain.usecase.RepositoryUsecase

class MockProfileDetailsUsecaseImpl : RepositoryUsecase.MockProfileDetailsUsecase {
    private val profiles: List<MockProfileDetails> = listOf(
        MockProfileDetails(
            "user1",
            "User 1",
            "01.02.2022",
            "user1@mail.com",
            ""
        ),
        MockProfileDetails(
            "user2",
            "User 2",
            "02.02.2022",
            "user2@mail.com",
            ""
        ),
        MockProfileDetails(
            "user3",
            "User 3",
            "03.02.2022",
            "user3@mail.com",
            ""
        ),
        MockProfileDetails(
            "user4",
            "User 4",
            "04.02.2022",
            "user4@mail.com",
            ""
        )
    )
    private lateinit var result: MockProfileDetails
    override fun receiveItem(login: String): MockProfileDetails {
        profiles.forEach { mockProfileDetails ->
            if (mockProfileDetails.login == login) {
                result = mockProfileDetails
            }
        }
        return result
    }
}