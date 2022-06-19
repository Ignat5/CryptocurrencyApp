package com.example.cryptocurrencyapp.domain.models

data class CryptoDetailState(
    val data: CryptoDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
