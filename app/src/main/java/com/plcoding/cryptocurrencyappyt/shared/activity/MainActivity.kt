package com.plcoding.cryptocurrencyappyt.shared.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plcoding.cryptocurrencyappyt.shared.navigation.Screen
import com.plcoding.cryptocurrencyappyt.feature.coin_detail.presentation.CoinDetailScreen
import com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.CoinListScreen
import com.plcoding.cryptocurrencyappyt.feature.menu.presentation.MenuScreen
import com.plcoding.cryptocurrencyappyt.feature.my_counter.prsesentation.CounterScreen
import com.plcoding.cryptocurrencyappyt.shared.ui.theme.CryptocurrencyAppYTTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrencyAppYTTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MenuScreen.route
                    ) {
                        composable(
                            route = Screen.MenuScreen.route
                        ) {
                            MenuScreen(navController)
                        }
                        composable(
                            route = Screen.CounterScreen.route
                        ) {
                            CounterScreen(navController)
                        }
                        composable(
                            route = Screen.CoinListScreen.route
                        ) {
                            CoinListScreen(navController)
                        }
                        composable(
                            route = Screen.CoinDetailScreen.route + "/{coinId}"
                        ) {
                            CoinDetailScreen()
                        }
                    }
                }
            }
        }
    }
}