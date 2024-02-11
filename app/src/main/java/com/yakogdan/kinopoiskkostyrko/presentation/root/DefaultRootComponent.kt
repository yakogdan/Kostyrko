package com.yakogdan.kinopoiskkostyrko.presentation.root

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.yakogdan.kinopoiskkostyrko.presentation.favourite.DefaultFavouriteComponent
import com.yakogdan.kinopoiskkostyrko.presentation.popular.DefaultPopularComponent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.parcelize.Parcelize

class DefaultRootComponent @AssistedInject constructor(
    private val favouriteComponentFactory: DefaultFavouriteComponent.Factory,
    private val popularComponentFactory: DefaultPopularComponent.Factory,
    @Assisted("componentContext") componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.Popular,
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child {
        return when (config) {
            Config.Favourite -> {
                val component = favouriteComponentFactory.create(
                    onFilmItemClicked = {
                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Favourite(component)
            }

            Config.Popular -> {
                val component = popularComponentFactory.create(
                    onFilmItemClicked = {
                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Popular(component)
            }
        }
    }

    sealed interface Config : Parcelable {

        @Parcelize
        data object Favourite : Config

        @Parcelize
        data object Popular : Config
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultRootComponent
    }
}