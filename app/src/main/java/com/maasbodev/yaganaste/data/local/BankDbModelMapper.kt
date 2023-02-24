package com.maasbodev.yaganaste.data.local

import com.maasbodev.yaganaste.domain.model.Bank

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
