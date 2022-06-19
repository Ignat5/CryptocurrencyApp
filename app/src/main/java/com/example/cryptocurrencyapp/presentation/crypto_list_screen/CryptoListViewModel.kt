package com.example.cryptocurrencyapp.presentation.crypto_list_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.domain.models.Crypto
import com.example.cryptocurrencyapp.domain.models.CryptoListState
import com.example.cryptocurrencyapp.domain.use_cases.GetAllCryptoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    private val getAllCryptoUseCase: GetAllCryptoUseCase
): ViewModel() {

    private val _cryptoState = mutableStateOf(CryptoListState())
    val cryptoState: State<CryptoListState> = _cryptoState

    init {
        getAllCrypto()
    }

    private fun getAllCrypto() {
        getAllCryptoUseCase().onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    _cryptoState.value = CryptoListState(resource.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _cryptoState.value = CryptoListState(isLoading = true)
                }
                is Resource.Error -> {
                    _cryptoState.value = CryptoListState(errorMessage = resource.message)
                }
            }
        }.launchIn(viewModelScope)
    }

}