package com.example.cryptocurrencyapp.domain.models

data class Crypto(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)
