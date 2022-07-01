package com.example.githubprofiles

import com.example.githubprofiles.data.GitHubApi
import com.example.githubprofiles.data.web.entity.WebProfileCommon
import com.example.githubprofiles.di.web.WebProfileListModule
import com.example.githubprofiles.domain.usecase.RepositoryUsecase
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Converter
import retrofit2.Retrofit

class DaggerModuleTest {

    private lateinit var daggerListProfileModule: WebProfileListModule
    private lateinit var baseUrl: String
    private lateinit var converterFactory: Converter.Factory
    private lateinit var retrofit: Retrofit
    private lateinit var gitHubApi: GitHubApi
    private lateinit var profileUsecase: RepositoryUsecase.WebProfileCommonUsecase
    private lateinit var profileList: List<WebProfileCommon>

    @Before
    fun setUp() {
        daggerListProfileModule = WebProfileListModule()
        baseUrl = daggerListProfileModule.getBaseUrl()
        converterFactory = daggerListProfileModule.getConverterFactory()
        retrofit = daggerListProfileModule.getRetrofit(baseUrl, converterFactory)
        gitHubApi = daggerListProfileModule.getGitHubApi(retrofit)
        profileUsecase = daggerListProfileModule.getWebProfileCommonUsecase(gitHubApi)

        profileList = profileUsecase.receive().blockingGet()
    }

    @Test
    fun daggerModuleTest_CorrectBaseUrl_ReturnsTrue() {
        assertEquals(baseUrl, "https://api.github.com/")
    }

    @Test
    fun daggerModuleTest_CorrectProfilesGetting_ReturnsTrue() {
        assertEquals(profileList[0].login, "mojombo")
    }

    @Test
    fun daggerModuleTest_IncorrectProfileGetting_ReturnsTrue() {
        assertNotEquals(profileList[0].login, "somename")
    }

}