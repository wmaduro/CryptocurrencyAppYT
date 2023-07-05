package com.plcoding.cryptocurrencyappyt.shared.navigation

sealed class Screen(val menuName:String? = null, val route: String) {
    object MenuScreen: Screen(route = "menu")
    object CoinListScreen: Screen(menuName = "Coin List", route = "coin_list_screen")
    object CoinDetailScreen: Screen(route = "coin_detail_screen")
    object CounterScreen: Screen(menuName = "Counter", route = "counter_screen")
    object FlowStudyScreen: Screen(menuName = "Flow Study", route = "flow_study_screen")
    object EffectHandler: Screen(menuName = "EffectHandler", route = "EffectHandler")
    object MaterialStudy: Screen(menuName = "MaterialStudy", route = "MaterialStudy")

}
