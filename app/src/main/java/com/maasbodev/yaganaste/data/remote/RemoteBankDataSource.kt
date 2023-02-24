package com.maasbodev.yaganaste.data.remote

import androidx.annotation.VisibleForTesting
import arrow.core.Either
import com.maasbodev.yaganaste.data.local.AppDatabase
import com.maasbodev.yaganaste.data.local.LocalBankDataSource
import com.maasbodev.yaganaste.data.local.toBank
import com.maasbodev.yaganaste.domain.model.Bank
import javax.inject.Inject

class RemoteBankDataSource @Inject constructor(
	appDatabase: AppDatabase,
	localBankDataSource: LocalBankDataSource,
	private val bankApiService: BankApiService,
) {

	@VisibleForTesting
	val localDataSource = localBankDataSource
	private val bankDao = appDatabase.bankDao()

	suspend fun getBanks(): Either<Throwable, List<Bank>> {
		val remoteBanks = bankApiService.getBanks()
		val banks = mutableListOf<Bank>()
		for (remoteBank in remoteBanks) {
			banks.add(remoteBank.toBank())
		}
		if (bankDao.getAll().isEmpty()) {
			localDataSource.saveBanks(banks)
		}
		return Either.catch { localDataSource.getBanks() }
	}
}