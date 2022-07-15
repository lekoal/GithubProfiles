package com.example.githubprofiles

import com.example.githubprofiles.data.GitHubApi
import com.example.githubprofiles.data.web.usecase.WebProfileCommonUsecaseImpl
import com.example.githubprofiles.data.web.usecase.WebProfileDetailsUsecaseImpl
import com.example.githubprofiles.data.web.usecase.WebRepoCommonUsecaseImpl
import com.example.githubprofiles.di.web.WebProfileListModule
import com.example.githubprofiles.domain.usecase.RepositoryUsecase
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Converter
import retrofit2.Retrofit

class WebProfileUsecaseImplTest {

    private lateinit var daggerListProfileModule: WebProfileListModule
    private lateinit var baseUrl: String
    private lateinit var converterFactory: Converter.Factory
    private lateinit var retrofit: Retrofit
    private lateinit var gitHubApi: GitHubApi
    private lateinit var webProfiles: RepositoryUsecase.WebProfileCommonUsecase
    private lateinit var webDetails: RepositoryUsecase.WebProfileDetailsUsecase
    private lateinit var webRepos: RepositoryUsecase.WebRepoCommonUsecase

    @Before
    fun setUp() {
        daggerListProfileModule = WebProfileListModule()
        baseUrl = daggerListProfileModule.getBaseUrl()
        converterFactory = daggerListProfileModule.getConverterFactory()
        retrofit = daggerListProfileModule.getRetrofit(baseUrl, converterFactory)
        gitHubApi = daggerListProfileModule.getGitHubApi(retrofit)
        webProfiles = WebProfileCommonUsecaseImpl(gitHubApi)
        webDetails = WebProfileDetailsUsecaseImpl(gitHubApi)
        webRepos = WebRepoCommonUsecaseImpl(gitHubApi)
    }

    @Test
    fun webProfileUsecaseImplTest_ProfileAvatarUrlEquals_ReturnsTrue() {
        val firstObject = webDetails.receive("ivey")?.blockingGet()
        val secondObject = webProfiles.receive().blockingGet()[5]
        assertEquals(firstObject?.avatarUrl, secondObject.avatarUrl)
    }

    @Test
    fun webProfileUsecaseImplTest_ProfileLoginNotEquals_ReturnsTrue() {
        val firstObject = webDetails.receive("ivey")?.blockingGet()
        val secondObject = webDetails.receive("defunct")?.blockingGet()
        assertNotEquals(firstObject, secondObject)
    }

    @Test
    fun webProfileUsecaseImplTest_ClonedArraysEquals_ReturnsTrue() {
        val originObject = webRepos.receive("ivey")?.blockingGet()?.toTypedArray()
        val cloneObject = webRepos.receiveClone()?.blockingGet()?.toTypedArray()
        assertArrayEquals(originObject, cloneObject)
    }

    @Test
    fun webProfileUsecaseImplTest_ProfileDetailsIsNull_ReturnsTrue() {
        assertNull(webDetails.receive(""))
    }

    @Test
    fun webProfileUsecaseImplTest_CommonProfileDataIsNotNull_ReturnsTrue() {
        assertNotNull(webProfiles.receive())
    }

    @Test
    fun webProfileUsecaseImplTest_CorrectObjectEquality_ReturnsTrue() {
        val originObject = webRepos.receive("ivey")
        val cloneObject = webRepos.receiveClone()
        assertSame(cloneObject, originObject)
    }

    @Test
    fun webProfileUsecaseImplTest_IncorrectObjectEquality_ReturnsTrue() {
        val firstObject = webRepos.receive("ivey")
        val secondObject = webRepos.receive("ivey")
        assertNotSame(firstObject, secondObject)
    }

}