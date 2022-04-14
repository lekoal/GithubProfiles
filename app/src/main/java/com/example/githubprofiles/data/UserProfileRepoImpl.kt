package com.example.githubprofiles.data

import com.example.githubprofiles.repo.UserProfileRepo
import com.example.githubprofiles.repo.datasource.GitHubRepositoryOwnerDTO
import com.example.githubprofiles.repo.datasource.GitHubUserProfileDetailsDTO
import com.example.githubprofiles.repo.datasource.GitHubUserProfileListItemDTO
import com.example.githubprofiles.repo.datasource.GitHubUserProfileRepoListItemDTO

object UserProfileRepoImpl : UserProfileRepo {

    override fun getUserProfileListItem(): List<GitHubUserProfileListItemDTO>? {
        val user1 = GitHubUserProfileListItemDTO(
            "", "", "", "", "", "", "",
            1, "user1", "", "", "", "", false,
            "", "", "", ""
        )

        val user2 = GitHubUserProfileListItemDTO(
            "", "", "", "", "", "", "",
            2, "user2", "", "", "", "", false,
            "", "", "", ""
        )

        val user3 = GitHubUserProfileListItemDTO(
            "", "", "", "", "", "", "",
            3, "user3", "", "", "", "", false,
            "", "", "", ""
        )
        return listOf(user1, user2, user3)
    }

    override fun getUserProfileDetails(userLogin: String): GitHubUserProfileDetailsDTO? {
        val user1 = GitHubUserProfileDetailsDTO(
            "", "", "", "", "01.02.2003", "user1@mail.com",
            "", 0, "", 0, "", "", "",
            "", "", 1, "", "user1", "User 1", "",
            "", 1, 2, "", "", false,
            "", "", "", "", "",""
        )

        val user2 = GitHubUserProfileDetailsDTO(
            "", "", "", "", "02.02.2003", "user2@mail.com",
            "", 0, "", 0, "", "", "",
            "", "", 2, "", "user2", "User 2", "",
            "", 1, 2, "", "", false,
            "", "", "", "", "",""
        )

        val user3 = GitHubUserProfileDetailsDTO(
            "", "", "", "", "03.02.2003", "user3@mail.com",
            "", 0, "", 0, "", "", "",
            "", "", 3, "", "user3", "User 3", "",
            "", 1, 2, "", "", false,
            "", "", "", "", "",""
        )
        val allUserProfiles = listOf(user1, user2, user3)
        var currentUserProfile: GitHubUserProfileDetailsDTO? = null

        allUserProfiles.forEach {
            if (it.login == userLogin) {
                currentUserProfile = it
            }
        }
        return currentUserProfile
    }

    override fun getUserProfileRepoListItem(userLogin: String): List<GitHubUserProfileRepoListItemDTO>? {
        val owner1 = GitHubRepositoryOwnerDTO(
            "", "", "", "", "", "", "",
            1, "user1", "", "", "", "", false,
            "", "", "", ""
        )

        val owner2 = GitHubRepositoryOwnerDTO(
            "", "", "", "", "", "", "",
            2, "user2", "", "", "", "", false,
            "", "", "", ""
        )

        val owner3 = GitHubRepositoryOwnerDTO(
            "", "", "", "", "", "", "",
            3, "user3", "", "", "", "", false,
            "", "", "", ""
        )

        val user1Repo1 = GitHubUserProfileRepoListItemDTO(
            true, "", false, "", "", "",
            "", "", "", "", "", "",
            "", "02.03.2003", "", "", "",
            false, "", "", false, 1, 1, "",
            "Repository 1", "", "", "", "", false,
            false, false, false, false, "", "",
            "", 1, false, "", "", "", "",
            "", "", "", "", "", "", "",
            "repo_1", "", "", 1, 1, owner1, false,
            "", "", "", 1, "", 1, "",
            "", "", "", "", "", "", listOf(""),
            "", "", "", "", 1, 1
        )

        val user1Repo2 = GitHubUserProfileRepoListItemDTO(
            true, "", false, "", "", "",
            "", "", "", "", "", "",
            "", "03.03.2003", "", "", "",
            false, "", "", false, 1, 1, "",
            "Repository 2", "", "", "", "", false,
            false, false, false, false, "", "",
            "", 2, false, "", "", "", "",
            "", "", "", "", "", "", "",
            "repo_2", "", "", 1, 1, owner1, false,
            "", "", "", 1, "", 1, "",
            "", "", "", "", "", "", listOf(""),
            "", "", "", "", 1, 1
        )

        val user2Repo1 = GitHubUserProfileRepoListItemDTO(
            true, "", false, "", "", "",
            "", "", "", "", "", "",
            "", "03.03.2003", "", "", "",
            false, "", "", false, 1, 1, "",
            "Repository 1", "", "", "", "", false,
            false, false, false, false, "", "",
            "", 3, false, "", "", "", "",
            "", "", "", "", "", "", "",
            "repo_1", "", "", 1, 1, owner2, false,
            "", "", "", 1, "", 1, "",
            "", "", "", "", "", "", listOf(""),
            "", "", "", "", 1, 1
        )

        val user2Repo2 = GitHubUserProfileRepoListItemDTO(
            true, "", false, "", "", "",
            "", "", "", "", "", "",
            "", "04.03.2003", "", "", "",
            false, "", "", false, 1, 1, "",
            "Repository 2", "", "", "", "", false,
            false, false, false, false, "", "",
            "", 4, false, "", "", "", "",
            "", "", "", "", "", "", "",
            "repo_2", "", "", 1, 1, owner2, false,
            "", "", "", 1, "", 1, "",
            "", "", "", "", "", "", listOf(""),
            "", "", "", "", 1, 1
        )

        val user3Repo1 = GitHubUserProfileRepoListItemDTO(
            true, "", false, "", "", "",
            "", "", "", "", "", "",
            "", "04.03.2003", "", "", "",
            false, "", "", false, 1, 1, "",
            "Repository 1", "", "", "", "", false,
            false, false, false, false, "", "",
            "", 5, false, "", "", "", "",
            "", "", "", "", "", "", "",
            "repo_1", "", "", 1, 1, owner3, false,
            "", "", "", 1, "", 1, "",
            "", "", "", "", "", "", listOf(""),
            "", "", "", "", 1, 1
        )

        val user3Repo2 = GitHubUserProfileRepoListItemDTO(
            true, "", false, "", "", "",
            "", "", "", "", "", "",
            "", "05.03.2003", "", "", "",
            false, "", "", false, 1, 1, "",
            "Repository 2", "", "", "", "", false,
            false, false, false, false, "", "",
            "", 6, false, "", "", "", "",
            "", "", "", "", "", "", "",
            "repo_2", "", "", 1, 1, owner3, false,
            "", "", "", 1, "", 1, "",
            "", "", "", "", "", "", listOf(""),
            "", "", "", "", 1, 1
        )
        val allRepos = listOf(user1Repo1, user1Repo2, user2Repo1, user2Repo2, user3Repo1, user3Repo2)
        val currentRepo = mutableListOf<GitHubUserProfileRepoListItemDTO>()

        allRepos.forEach {
            if (it.owner?.login == userLogin) {
                currentRepo.add(it)
            }
        }
        return currentRepo
    }

}