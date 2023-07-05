package com.plcoding.cryptocurrencyappyt.feature.material_study.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun MaterialStudyScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        ModalBottomSheetSample()
    }
}


@Composable
@OptIn(ExperimentalMaterialApi::class)
fun ModalBottomSheetSample() {
    val state = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetState = state,
        sheetShape = MaterialTheme.shapes.small,
        sheetContent = {
            Box(Modifier.height(400.dp)) {
//                Column() {
//                    Text(text = "item 1", Modifier.clickable {
//                        println("madurox itme 1")
//                    })
//                    Text(text = "item 2", Modifier.clickable {
//                        println("madurox itme 2")
//                    })
//                }
            LazyColumn {
                items(2) {
                    ListItem(
                        text = { Text("Item $it") },
                        icon = {
                            Icon(
                                Icons.Default.Favorite,
                                contentDescription = "Localized description"
                            )
                        }
                    )
                }
            }
            }
        }
    ) {
        Content(state, scope)
    }
}


@Composable
@OptIn(ExperimentalMaterialApi::class)
private fun Content(sheetState: ModalBottomSheetState, sheetScope: CoroutineScope) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Rest of the UI")
        Spacer(Modifier.height(20.dp))
        Button(onClick = { sheetScope.launch { sheetState.show() } }) {
            Text("Click to show sheet")
        }
    }
}