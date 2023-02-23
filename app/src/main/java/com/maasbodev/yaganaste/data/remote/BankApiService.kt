package com.maasbodev.yaganaste.data.remote

import com.maasbodev.yaganaste.data.remote.model.Bank
import retrofit2.http.GET

interface BankApiService {

	@GET(BANKS_END_POINT)
	suspend fun getBanks(): List<Bank>
}

private const val BANKS_END_POINT = "banks"