package com.example.cryptocurrencyapp.data.repository

import com.example.cryptocurrencyapp.data.dto.CryptoDetailDto
import com.example.cryptocurrencyapp.data.dto.CryptoDto

interface CryptoRepository {

    suspend fun getAllCrypto(): List<CryptoDto>

    suspend fun getCryptoById(id: String): CryptoDetailDto

}