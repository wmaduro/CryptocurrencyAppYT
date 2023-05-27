package com.plcoding.cryptocurrencyappyt.feature.coin_list.domain.use_case

import com.plcoding.cryptocurrencyappyt.shared.common.Resource
import com.plcoding.cryptocurrencyappyt.shared.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FakeHeaderUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading<String>())
            val data = repository.getFakeData(3000L)
            emit(Resource.Success<String>(data))
        } catch (e: HttpException) {
            emit(Resource.Error<String>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<String>("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            emit(Resource.Error<String>(e.message ?: ""))
        }
    }
}