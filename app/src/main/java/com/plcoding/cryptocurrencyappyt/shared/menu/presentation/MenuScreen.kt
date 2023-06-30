package com.plcoding.cryptocurrencyappyt.shared.menu.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.plcoding.cryptocurrencyappyt.shared.navigation.Screen

@Composable
fun MenuScreen(
    navController: NavController,
) {
    val screens: List<Screen> = Screen::class.sealedSubclasses.mapNotNull { subclass ->
        subclass.objectInstance
    }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.background(Color.White) // Set background color to white
    ) {
        val screensFiltered = screens.filter { screen ->
            screen.menuName != null
        }

        items(
            items = screensFiltered,
            key = { screen ->
                screen.hashCode()
            },
            itemContent = { screen ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(vertical = 5.dp)
                        .clickable {
                            navController.navigate(screen.route)
                        },
                    backgroundColor = Color.Green
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        screen.menuName?.let { menuName ->
                            Text(
                                text = menuName,
                                textAlign = TextAlign.Center,
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        )
    }
}


private fun buildItems(screens: List<Screen>): List<@Composable () -> Unit> {
    val screensFiltered = screens.filter { screen ->
        screen.menuName != null
    }

    val composableList: List<@Composable () -> Unit> = screensFiltered.map { screen ->
        {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = screen.menuName!!,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
    return composableList
}
