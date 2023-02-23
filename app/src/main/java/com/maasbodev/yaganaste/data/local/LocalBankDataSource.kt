package com.maasbodev.yaganaste.data.local

import androidx.annotation.VisibleForTesting
import com.maasbodev.yaganaste.di.IO
import com.maasbodev.yaganaste.domain.model.Bank
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LocalBankDataSource @Inject constructor(
	appDatabase: AppDatabase,
	@IO private val dispatcher: CoroutineDispatcher,
) {

	@VisibleForTesting
	val bankDao = appDatabase.bankDao()

	suspend fun getBanks(): List<Bank> = withContext(dispatcher) {
		bankDao.getAll().map {
			it.toBank()
		}
	}

	suspend fun saveBanks(banks: List<Bank>) = withContext(dispatcher) {
		bankDao.insert(banks.toBankDbModels())
	}
}