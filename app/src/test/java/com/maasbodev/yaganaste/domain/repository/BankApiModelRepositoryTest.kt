package com.maasbodev.yaganaste.domain.repository

import arrow.core.left
import arrow.core.right
import com.maasbodev.yaganaste.data.local.LocalBankDataSource
import com.maasbodev.yaganaste.data.remote.BankApiModelDataSource
import com.maasbodev.yaganaste.domain.model.Bank
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeTrue
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BankApiModelRepositoryTest {

    private val localBankDataSource: LocalBankDataSource = mockk()
    private val bankApiModelDataSource: BankApiModelDataSource = mockk()
    private lateinit var repository: BankRepository

    @Before
    fun setUp() {
        repository = BankRepository(
            localBankDataSource,
            bankApiModelDataSource,
        )
    }

    @Test
    fun `GIVEN successful response WHEN getBanks THEN get the expected result`() = runTest {
        val banks = listOf(buildBank())
        coEvery { bankApiModelDataSource.getBanks() } returns banks.right()
        coEvery { localBankDataSource.saveBanks(banks) } returns Unit
        coEvery { localBankDataSource.getBanks() } returns banks

        repository.getBanks()

        coVerify {
            bankApiModelDataSource.getBanks()
            localBankDataSource.saveBanks(banks)
            localBankDataSource.getBanks()
        }
    }

    @Test
    fun `GIVEN a failure banks WHEN getBanks THEN get the expected error`() = runTest {
        coEvery { bankApiModelDataSource.getBanks() } returns Throwable().left()

        val result = repository.getBanks()

        result.isLeft().shouldBeTrue()
        coVerify { bankApiModelDataSource.getBanks() }
    }

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
