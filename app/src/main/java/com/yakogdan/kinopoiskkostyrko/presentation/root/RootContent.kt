package com.yakogdan.kinopoiskkostyrko.presentation.root

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.yakogdan.kinopoiskkostyrko.presentation.favourite.FavouriteContent
import com.yakogdan.kinopoiskkostyrko.presentation.popular.PopularContent
import com.yakogdan.kinopoiskkostyrko.presentation.ui.theme.KinopoiskKostyrkoTheme

@Composable
fun RootContent(component: RootComponent) {
    KinopoiskKostyrkoTheme {
        Scaffold(
            topBar = {
                Text(text = "Популярные", fontSize = 25.sp)
            },
            bottomBar = {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {

                        }
                    ) {
                        Text(text = "1")
                    }
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {

                        }
                    ) {
                        Text(text = "2")
                    }
                }
            }
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                Children(stack = component.stack) {
                    when (val instance = it.instance) {
                        is RootComponent.Child.Favourite -> {
                            FavouriteContent(component = instance.component)
                        }

                        is RootComponent.Child.Popular -> {
                            PopularContent(component = instance.component)
                        }
                    }
                }
            }
        }
    }
}