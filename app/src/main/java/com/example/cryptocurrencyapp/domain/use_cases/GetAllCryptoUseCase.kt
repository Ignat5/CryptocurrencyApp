package com.example.cryptocurrencyapp.domain.use_cases

import com.example.cryptocurrencyapp.common.Resource
import com.example.cryptocurrencyapp.data.dto.toCrypto
import com.example.cryptocurrencyapp.data.repository.CryptoRepository
import com.example.cryptocurrencyapp.domain.models.Crypto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllCryptoUseCase constructor(
    @Inject private val repository: CryptoRepository
) {
    operator fun invoke(): Flow<Resource<List<Crypto>>> = flow {
        try {
            emit(Resource.Loading())
            val allCryptoList = repository.getAllCrypto().map { it.toCrypto() }
            emit(Resource.Success(allCryptoList))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage))
        }
        catch (e: IOException) {
            emit(Resource.Error(message = "No internet error"))
        }
    }
}