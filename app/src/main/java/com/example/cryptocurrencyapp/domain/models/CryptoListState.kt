package com.example.cryptocurrencyapp.domain.models

data class CryptoListState(
    val data: List<Crypto> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
