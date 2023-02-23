package com.maasbodev.yaganaste.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maasbodev.yaganaste.di.IO
import com.maasbodev.yaganaste.domain.model.Bank
import com.maasbodev.yaganaste.domain.repository.BankRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
	private val bankRepository: BankRepository,
	@IO private val dispatcher: CoroutineDispatcher,
) : ViewModel() {

	private val _state = MutableStateFlow<ViewState>(ViewState.Loading)
	val state: StateFlow<ViewState>
		get() = _state

	init {
		viewModelScope.launch(dispatcher) {
			bankRepository.getBanks()
				.fold(
					{
						_state.value = ViewState.Error(it)
					},
					{ banks ->
						_state.value = ViewState.Success(banks)
					}
				)
		}
	}

	sealed class ViewState {
		object Loading : ViewState()
		data class Success(val banks: List<Bank>) : ViewState()
		data class Error(val throwable: Throwable) : ViewState()
	}
}