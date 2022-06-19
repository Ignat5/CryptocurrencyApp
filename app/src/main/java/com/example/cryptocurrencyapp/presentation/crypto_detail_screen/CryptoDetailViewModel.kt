package com.example.cryptocurrencyapp.presentation.crypto_detail_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.common.Constants
import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.domain.models.CryptoDetailState
import com.example.cryptocurrencyapp.domain.models.CryptoListState
import com.example.cryptocurrencyapp.domain.use_cases.GetAllCryptoUseCase
import com.example.cryptocurrencyapp.domain.use_cases.GetCryptoDetailByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    private val getCryptoDetailByIdUseCase: GetCryptoDetailByIdUseCase,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _cryptoState = mutableStateOf(CryptoDetailState())
    val cryptoState: State<CryptoDetailState> = _cryptoState

    init {
        savedStateHandle.get<String>(Constants.CRYPTO_SAVED_STATE_ID)?.let { cryptoId ->
            getCryptoDetailById(cryptoId)
        }
    }

    private fun getCryptoDetailById(cryptoId: String) {
        getCryptoDetailByIdUseCase(cryptoId).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    _cryptoState.value = CryptoDetailState(data = resource.data)
                }
                is Resource.Loading -> {
                    _cryptoState.value = CryptoDetailState(isLoading = true)
                }
                is Resource.Error -> {
                    _cryptoState.value = CryptoDetailState(error = resource.message)
                }
            }
        }.launchIn(viewModelScope)
    }
}