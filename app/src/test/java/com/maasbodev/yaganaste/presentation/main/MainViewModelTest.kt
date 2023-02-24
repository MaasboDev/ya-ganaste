package com.maasbodev.yaganaste.presentation.main

import arrow.core.left
import arrow.core.right
import com.maasbodev.yaganaste.domain.model.Bank
import com.maasbodev.yaganaste.domain.repository.BankRepository
import com.maasbodev.yaganaste.presentation.screens.main.MainViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    private val bankRepository: BankRepository = mockk(relaxed = true)
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel(bankRepository, testDispatcher)
    }

    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN a success banks WHEN getBanks THEN get the expected call`() = runTest {
        val banks = buildBanks()
        coEvery { bankRepository.getBanks() } returns banks.right()

        coVerify { bankRepository.getBanks() }
    }

    @Test
    fun `GIVEN a failure banks WHEN getBanks THEN get the expected error`() = runTest {
        coEvery { bankRepository.getBanks() } returns Throwable().left()

        coVerify { bankRepository.getBanks() }
    }

    private fun buildBanks() = listOf(
        buildBank(),
        buildBank(),
        buildBank(),
    )

    private fun buildBank() = Bank(
        bankName = BANK_API_MODEL_NAME,
        description = DESCRIPTION,
        age = AGE,
        url = URL,
    )
}

private const val BANK_API_MODEL_NAME = "A Bank Name"
private const val DESCRIPTION = "A Bank Description"
private const val AGE = 1
private const val URL = "A Url"
