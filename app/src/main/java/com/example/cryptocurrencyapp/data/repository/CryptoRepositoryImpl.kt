package com.example.cryptocurrencyapp.data.repository

import com.example.cryptocurrencyapp.data.dto.CryptoDetailDto
import com.example.cryptocurrencyapp.data.dto.CryptoDto
import com.example.cryptocurrencyapp.data.remote.CoinPaprikaApi
import javax.inject.Inject

class CryptoRepositoryImpl constructor(
    @Inject private val api: CoinPaprikaApi): CryptoRepository {

    override suspend fun getAllCrypto(): List<CryptoDto> =
        api.getAllCrypto()

    override suspend fun getCryptoById(id: String): CryptoDetailDto =
        api.getCryptoById(id)
}