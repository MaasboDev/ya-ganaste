package com.maasbodev.yaganaste.domain.repository

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.maasbodev.yaganaste.data.local.LocalBankDataSource
import com.maasbodev.yaganaste.data.remote.BankApiModelDataSource
import com.maasbodev.yaganaste.domain.model.Bank
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BankRepository @Inject constructor(
    private val localBankDataSource: LocalBankDataSource,
    private val bankApiModelDataSource: BankApiModelDataSource
) {

    suspend fun getBanks(): Either<Throwable, List<Bank>> {
        bankApiModelDataSource.getBanks()
            .fold(
                {
                    return it.left()
                }, { banks ->
                    localBankDataSource.saveBanks(banks)
                }
            )
        return localBankDataSource.getBanks().right()
    }
}
