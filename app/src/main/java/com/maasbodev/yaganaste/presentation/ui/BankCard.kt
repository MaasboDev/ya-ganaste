package com.maasbodev.yaganaste.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.maasbodev.yaganaste.R
import com.maasbodev.yaganaste.domain.model.Bank

@Composable
fun BankCard(bank: Bank) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(8.dp),
    ) {
        Box {
            Surface(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = .3f),
                modifier = Modifier.align(Alignment.BottomCenter),
                contentColor = MaterialTheme.colorScheme.surface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    AsyncImage(
                        model = bank.url,
                        contentDescription = stringResource(R.string.bank_icon),
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(100.dp)
                    )
                    Text(text = bank.bankName)
                    Text(text = bank.description)
                }
            }
        }
    }
}
