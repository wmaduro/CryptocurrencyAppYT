package com.plcoding.cryptocurrencyappyt.shared.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plcoding.cryptocurrencyappyt.feature.coin_detail.presentation.CoinDetailScreen
import com.plcoding.cryptocurrencyappyt.feature.coin_list.presentation.CoinListScreen
import com.plcoding.cryptocurrencyappyt.feature.effecthandlers.presentation.EffectHandlerScreen
import com.plcoding.cryptocurrencyappyt.feature.flowstudy.presentation.FlowStudyScreen
import com.plcoding.cryptocurrencyappyt.feature.material_study.view.MaterialStudyScreen
import com.plcoding.cryptocurrencyappyt.feature.menu.presentation.MenuScreen
import com.plcoding.cryptocurrencyappyt.feature.my_counter.prsesentation.CounterScreen
import com.plcoding.cryptocurrencyappyt.shared.navigation.Screen
import com.plcoding.cryptocurrencyappyt.shared.ui.theme.CryptocurrencyAppYTTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Date

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            val scaffoldState = rememberScaffoldState()
//            val scope = rememberCoroutineScope()

//            Scaffold(
//                modifier = Modifier.fillMaxSize(),
//                scaffoldState = scaffoldState
//            ) {

//                Button(onClick = {
//                    scope.launch {
//                        scaffoldState.snackbarHostState.showSnackbar("${Date().time}")
//                    }
//                }) {}
                CryptocurrencyAppYTTheme {
                    Surface(color = MaterialTheme.colors.background) {
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = Screen.MenuScreen.route
                        ) {
                            composable(
                                route = Screen.MaterialStudy.route
                            ) {
                                MaterialStudyScreen()
                            }
                            composable(
                                route = Screen.EffectHandler.route
                            ) {
                                EffectHandlerScreen()
                            }
                            composable(
                                route = Screen.FlowStudyScreen.route
                            ) {
                                FlowStudyScreen()
                            }
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
//    }
}