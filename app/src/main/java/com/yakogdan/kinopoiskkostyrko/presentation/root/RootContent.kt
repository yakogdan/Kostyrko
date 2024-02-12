package com.yakogdan.kinopoiskkostyrko.presentation.root

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.yakogdan.kinopoiskkostyrko.presentation.favourite.FavouriteContent
import com.yakogdan.kinopoiskkostyrko.presentation.films.FilmsContent
import com.yakogdan.kinopoiskkostyrko.presentation.ui.theme.KinopoiskKostyrkoTheme

@Composable
fun RootContent(component: RootComponent) {
    KinopoiskKostyrkoTheme {
        Column(modifier = Modifier) {
            Children(stack = component.stack) {
                when (val instance = it.instance) {
                    is RootComponent.Child.Favourite -> {
                        FavouriteContent(component = instance.component)
                    }

                    is RootComponent.Child.Popular -> {
                        FilmsContent(component = instance.component)
                    }
                }
            }
        }
    }
}