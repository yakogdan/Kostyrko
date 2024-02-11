package com.yakogdan.kinopoiskkostyrko.presentation.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.yakogdan.kinopoiskkostyrko.presentation.favourite.FavouriteContent
import com.yakogdan.kinopoiskkostyrko.presentation.popular.PopularContent
import com.yakogdan.kinopoiskkostyrko.presentation.ui.theme.KinopoiskKostyrkoTheme

@Composable
fun RootContent(component: RootComponent) {
    KinopoiskKostyrkoTheme {
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