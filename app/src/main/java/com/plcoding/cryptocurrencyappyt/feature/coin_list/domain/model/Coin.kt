package com.plcoding.cryptocurrencyappyt.feature.coin_list.domain.model

import com.google.gson.annotations.SerializedName

data class Coin(
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)
