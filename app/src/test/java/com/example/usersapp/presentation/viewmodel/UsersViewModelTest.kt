package com.example.usersapp.presentation.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.usersapp.data.model.Profile
import com.example.usersapp.data.model.User
import com.example.usersapp.data.util.Resource
import com.example.usersapp.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.mockito.Mockito
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert
import org.junit.rules.RuleChain
import org.mockito.kotlin.*

@ExperimentalCoroutinesApi
class MainCoroutineRule :
    TestWatcher() {

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}

class UsersViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val instantLiveDataAndCoroutineRules: RuleChain
        get() = RuleChain
            .outerRule(MainCoroutineRule())
            .around(InstantTaskExecutorRule())

    private val applicationMock = Mockito.mock(Application::class.java)
    private val getUsersUseCaseMock: GetUsersUseCase = mock()
    private val viewModel: UsersViewModel = UsersViewModel(
        applicationMock,
        getUsersUseCaseMock,
    )

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = instantLiveDataAndCoroutineRules

    @Test
    fun getUsers_initialState_loadingStateReceived() = runBlocking {
        val uiState = MutableStateFlow<Resource>(Resource.Loading)
        Assert.assertEquals(uiState.value, viewModel.uiState.value)
    }

    @Test
    fun getUsersLines_sentRequest_successWithUsersReceived() = runBlocking {
        val url = "https://6185073a23a2fe0017fff312.mockapi.io/"
        val user = User(
            "", "bruno", "123", listOf(), "", Profile("", "", listOf()),
            ""
        )
        val listOfUserSuccess = Resource.Success(
            listOf(user)
        )
        whenever(getUsersUseCaseMock.execute(any())) doReturn listOfUserSuccess
        viewModel.getUsers(url)
        Assert.assertEquals(
            listOfUserSuccess, viewModel.uiState.value
        )
    }

    @Test
    fun getUsersLines_sentRequest_errorReceived() = runBlocking {
        val url = "https://6185073a23a2fe0017fff312.mockapi.io/"
        whenever(getUsersUseCaseMock.execute(any())).doAnswer { throw java.lang.Exception("") }
        viewModel.getUsers(url)
        assertThat(viewModel.uiState.value, instanceOf(Resource.Error::class.java))
    }
}
