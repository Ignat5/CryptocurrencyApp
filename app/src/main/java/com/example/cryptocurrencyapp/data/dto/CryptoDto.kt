package com.example.cryptocurrencyapp.data.dto

import com.example.cryptocurrencyapp.common.Constants
import com.example.cryptocurrencyapp.domain.models.Crypto
import com.google.gson.annotations.SerializedName

data class CryptoDto(
    val id: String,
    @SerializedName(Constants.IS_ACTIVE_FIELD)
    val isActive: Boolean,
    @SerializedName(Constants.IS_NEW_FIELD)
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CryptoDto.toCrypto(): Crypto = Crypto(
    id,
    isNew,
    name,
    rank,
    symbol,
)

