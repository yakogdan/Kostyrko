package com.yakogdan.kinopoiskkostyrko.presentation.root

import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import com.yakogdan.kinopoiskkostyrko.presentation.favourite.FavouriteComponent
import com.yakogdan.kinopoiskkostyrko.presentation.films.FilmsComponent

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {

        data class Favourite(val component: FavouriteComponent) : Child

        data class Popular(val component: FilmsComponent) : Child
    }
}