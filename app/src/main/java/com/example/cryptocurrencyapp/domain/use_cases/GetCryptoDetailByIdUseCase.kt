package com.example.cryptocurrencyapp.domain.use_cases

import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.data.dto.CryptoDetailDto
import com.example.cryptocurrencyapp.data.dto.toCrypto
import com.example.cryptocurrencyapp.data.dto.toCryptoDetail
import com.example.cryptocurrencyapp.data.repository.CryptoRepository
import com.example.cryptocurrencyapp.domain.models.Crypto
import com.example.cryptocurrencyapp.domain.models.CryptoDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCryptoDetailByIdUseCase @Inject constructor(
    private val repository: CryptoRepository
) {
    operator fun invoke(cryptoId: String): Flow<Resource<CryptoDetail>> = flow {
        try {
            emit(Resource.Loading<CryptoDetail>())
            val cryptoDetail = repository.getCryptoById(cryptoId).toCryptoDetail()
            emit(Resource.Success(cryptoDetail))
        } catch (e: HttpException) {
            emit(Resource.Error<CryptoDetail>(message = e.localizedMessage))
        }
        catch (e: IOException) {
            emit(Resource.Error<CryptoDetail>(message = "No internet error"))
        }
    }
}