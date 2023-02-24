package com.maasbodev.yaganaste.presentation.bank

import com.maasbodev.yaganaste.data.local.BankDbModel
import com.maasbodev.yaganaste.data.local.toBank
import com.maasbodev.yaganaste.data.local.toBankDbModel
import com.maasbodev.yaganaste.data.local.toBankDbModels
import com.maasbodev.yaganaste.data.remote.model.BankApiModel
import com.maasbodev.yaganaste.data.remote.toBank
import com.maasbodev.yaganaste.domain.model.Bank
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test

class BankApiModelMapperTest {
    @Test
    fun `GIVEN a BankApiModel WHEN toBank THEN return the expected Bank`() {
        val bankApiModel = buildBankApiModel()

        val result = bankApiModel.toBank()

        result.bankName shouldBeEqualTo BANK_API_MODEL_NAME
        result.description shouldBeEqualTo DESCRIPTION
        result.age shouldBeEqualTo AGE
        result.url shouldBeEqualTo URL
    }

    @Test
    fun `GIVEN a BankDbModel WHEN toBank THEN return the expected Bank`() {
        val bankDbModel = buildBankDbModel()

        val result = bankDbModel.toBank()

        result.bankName shouldBeEqualTo BANK_API_MODEL_NAME
        result.description shouldBeEqualTo DESCRIPTION
        result.age shouldBeEqualTo AGE
        result.url shouldBeEqualTo URL
    }

    @Test
    fun `GIVEN a Bank list WHEN toBankDbModel THEN return the expected BankDbModels`() {
        val bankList = listOf(buildBank())

        val result = bankList.toBankDbModels()

        result.forEach { bankDbModel ->
            bankDbModel.bankName shouldBeEqualTo BANK_API_MODEL_NAME
            bankDbModel.description shouldBeEqualTo DESCRIPTION
            bankDbModel.age shouldBeEqualTo AGE
            bankDbModel.url shouldBeEqualTo URL
        }
    }

    @Test
    fun `GIVEN a Bank WHEN toBankDbModel THEN return the expected BankDbModel`() {
        val bank = buildBank()

        val result = bank.toBankDbModel()

        result.bankName shouldBeEqualTo BANK_API_MODEL_NAME
        result.description shouldBeEqualTo DESCRIPTION
        result.age shouldBeEqualTo AGE
        result.url shouldBeEqualTo URL
    }

    private fun buildBankApiModel() = BankApiModel(
        bankName = BANK_API_MODEL_NAME,
        description = DESCRIPTION,
        age = AGE,
        url = URL,
    )

    private fun buildBankDbModel() = BankDbModel(
        bankName = BANK_API_MODEL_NAME,
        description = DESCRIPTION,
        age = AGE,
        url = URL,
    )

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
