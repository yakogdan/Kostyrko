package com.yakogdan.kinopoiskkostyrko.presentation.root

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.yakogdan.kinopoiskkostyrko.presentation.details.DetailsContent
import com.yakogdan.kinopoiskkostyrko.presentation.films.FilmsContent
import com.yakogdan.kinopoiskkostyrko.presentation.ui.theme.KinopoiskKostyrkoTheme

@Composable
fun RootContent(component: RootComponent) {
    KinopoiskKostyrkoTheme {
        Column(modifier = Modifier) {
            Children(stack = component.stack) {
                when (val instance = it.instance) {
                    is RootComponent.Child.Details -> {
                        DetailsContent(component = instance.component)
                    }

                    is RootComponent.Child.Films -> {
                        FilmsContent(component = instance.component)
                    }
                }
            }
        }
    }
}