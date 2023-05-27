package com.plcoding.cryptocurrencyappyt.shared.domain.repository

import com.plcoding.cryptocurrencyappyt.shared.data.remote.dto.CoinDetailDto
import com.plcoding.cryptocurrencyappyt.shared.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto

    suspend fun getFakeData(delay: Long): String
}