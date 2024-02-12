package com.yakogdan.kinopoiskkostyrko.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.yakogdan.kinopoiskkostyrko.presentation.details.DetailsComponent
import com.yakogdan.kinopoiskkostyrko.presentation.films.FilmsComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {

        data class Details(val component: DetailsComponent) : Child

        data class Films(val component: FilmsComponent) : Child
    }
}