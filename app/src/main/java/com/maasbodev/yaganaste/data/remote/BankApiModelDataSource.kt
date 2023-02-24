package com.maasbodev.yaganaste.data.remote

import androidx.annotation.VisibleForTesting
import arrow.core.Either
import com.maasbodev.yaganaste.data.local.AppDatabase
import com.maasbodev.yaganaste.data.local.LocalBankDataSource
import com.maasbodev.yaganaste.domain.model.Bank
import javax.inject.Inject

class BankApiModelDataSource @Inject constructor(
    appDatabase: AppDatabase,
    localBankDataSource: LocalBankDataSource,
    private val bankApiService: BankApiService,
) {

    @VisibleForTesting
    val localDataSource = localBankDataSource
    private val bankDao = appDatabase.bankDao()

    suspend fun getBanks(): Either<Throwable, List<Bank>> {
        val bankApiModels = bankApiService.getBanks()
        val banks = mutableListOf<Bank>()
        for (bankApiModel in bankApiModels) {
            banks.add(bankApiModel.toBank())
        }
        if (bankDao.getAll().isEmpty()) {
            localDataSource.saveBanks(banks)
        }
        return Either.catch { localDataSource.getBanks() }
    }
}
