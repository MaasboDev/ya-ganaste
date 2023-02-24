package com.maasbodev.yaganaste.domain.repository

import arrow.core.left
import arrow.core.right
import com.maasbodev.yaganaste.data.local.LocalBankDataSource
import com.maasbodev.yaganaste.data.remote.RemoteBankDataSource
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
class BankRepositoryTest {

	private val localBankDataSource: LocalBankDataSource = mockk()
	private val remoteBankDataSource: RemoteBankDataSource = mockk()
	private lateinit var repository: BankRepository

	@Before
	fun serUp() {
		repository = BankRepository(
			localBankDataSource,
			remoteBankDataSource,
		)
	}

	@Test
	fun `GIVEN successful response WHEN getBanks THEN get the expected result`() = runTest {
		val banks = listOf(buildBank())
		coEvery { remoteBankDataSource.getBanks() } returns banks.right()
		coEvery { localBankDataSource.saveBanks(banks) } returns Unit
		coEvery { localBankDataSource.getBanks() } returns banks

		repository.getBanks()

		coVerify {
			remoteBankDataSource.getBanks()
			localBankDataSource.saveBanks(banks)
			localBankDataSource.getBanks()
		}
	}

	@Test
	fun `GIVEN a failure banks WHEN getBanks THEN get the expected error`() = runTest {
		coEvery { remoteBankDataSource.getBanks() } returns Throwable().left()

		val result = repository.getBanks()

		result.isLeft().shouldBeTrue()
		coVerify { remoteBankDataSource.getBanks() }
	}

	private fun buildBank() = Bank(
		bankName = BANK_NAME,
		description = DESCRIPTION,
		age = AGE,
		url = URL,
	)
}

private const val BANK_NAME = "A Bank Name"
private const val DESCRIPTION = "A Bank Description"
private const val AGE = 1
private const val URL = "A Url"