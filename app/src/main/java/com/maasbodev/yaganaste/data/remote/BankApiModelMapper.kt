package com.maasbodev.yaganaste.data.remote

import com.maasbodev.yaganaste.data.remote.model.BankApiModel
import com.maasbodev.yaganaste.domain.model.Bank

fun BankApiModel.toBank(): Bank = Bank(
    bankName = bankName,
    description = description,
    age = age,
    url = url,
)
