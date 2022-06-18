package com.example.cryptocurrencyapp.data.remote

import com.example.cryptocurrencyapp.common.Constants
import com.example.cryptocurrencyapp.data.dto.CryptoDetailDto
import com.example.cryptocurrencyapp.data.dto.CryptoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

@GET(Constants.GET_ALL_CRYPTOS)
suspend fun getAllCrypto(): List<CryptoDto>

@GET(Constants.GET_CRYPTO_BY_ID)
suspend fun getCryptoById(
    @Path(Constants.CRYPTO_ID) id: String
): CryptoDetailDto

}