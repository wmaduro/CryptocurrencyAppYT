package com.plcoding.cryptocurrencyappyt.shared.data.repository

import com.plcoding.cryptocurrencyappyt.shared.data.remote.CoinPaprikaApi
import com.plcoding.cryptocurrencyappyt.shared.data.remote.dto.CoinDetailDto
import com.plcoding.cryptocurrencyappyt.shared.data.remote.dto.CoinDto
import com.plcoding.cryptocurrencyappyt.shared.domain.repository.CoinRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {

    override suspend fun getFakeData(delay: Long): String {
        delay(delay)
        return "Fake 1.........."
    }

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }


}