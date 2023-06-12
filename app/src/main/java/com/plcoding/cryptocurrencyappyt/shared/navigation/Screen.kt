package com.plcoding.cryptocurrencyappyt.shared.navigation

sealed class Screen(val menuName:String? = null, val route: String) {
    object MenuScreen: Screen(route = "menu")
    object CoinListScreen: Screen(menuName = "Coin List", route = "coin_list_screen")
    object CoinDetailScreen: Screen(route = "coin_detail_screen")
    object CounterScreen: Screen(menuName = "Counter", route = "counter_screen")
}
