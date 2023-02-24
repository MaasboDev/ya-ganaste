package com.maasbodev.yaganaste.presentation.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.maasbodev.yaganaste.R
import com.maasbodev.yaganaste.presentation.ui.BankCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val mainViewModel = viewModel(modelClass = MainViewModel::class.java)
    val state by mainViewModel.state.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(stringResource(id = R.string.app_name)) })
            }
        ) { padding ->
            when (state) {
                MainViewModel.ViewState.Loading -> LazyColumn {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(padding)
                                .fillMaxSize()
                                .wrapContentSize(align = Alignment.Center)
                        )
                    }
                }
                is MainViewModel.ViewState.Error -> Text(
                    text = stringResource(R.string.download_error),
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                        .wrapContentHeight(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Black,
                    fontStyle = FontStyle.Italic,
                    fontSize = 32.sp
                )
                is MainViewModel.ViewState.Success -> LazyColumn(modifier = Modifier.padding(padding)) {
                    items((state as MainViewModel.ViewState.Success).banks) { Bank ->
                        Box(modifier = Modifier.padding(4.dp)) {
                            BankCard(bank = Bank)
                        }
                    }
                }
            }
        }
    }
}
