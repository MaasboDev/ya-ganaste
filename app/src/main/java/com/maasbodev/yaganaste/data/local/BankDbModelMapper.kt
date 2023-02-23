package com.maasbodev.yaganaste.data.local

import com.maasbodev.yaganaste.domain.model.Bank
import com.maasbodev.yaganaste.data.remote.model.Bank as RemoteBank

fun RemoteBank.toBank(): Bank = Bank(
	bankName = bankName,
	description = description,
	age = age,
	url = url,
)

fun BankDbModel.toBank(): Bank = Bank(
	bankName = bankName,
	description = description,
	age = age,
	url = url,
)

fun List<Bank>.toBankDbModels(): List<BankDbModel> = map { it.toBankDbModel() }

fun Bank.toBankDbModel(): BankDbModel = BankDbModel(
	bankName = bankName,
	description = description,
	age = age,
	url = url,
)