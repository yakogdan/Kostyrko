package com.yakogdan.kinopoiskkostyrko.presentation.root

import android.os.Parcelable
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.yakogdan.kinopoiskkostyrko.domain.entity.Film
import com.yakogdan.kinopoiskkostyrko.presentation.details.DefaultDetailsComponent
import com.yakogdan.kinopoiskkostyrko.presentation.films.DefaultFilmsComponent
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.parcelize.Parcelize

class DefaultRootComponent @AssistedInject constructor(
    private val defaultDetailsComponent: DefaultDetailsComponent.Factory,
    private val filmsComponentFactory: DefaultFilmsComponent.Factory,
    @Assisted("componentContext") componentContext: ComponentContext
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = navigation,
        initialConfiguration = Config.Films,
        handleBackButton = true,
        childFactory = ::child
    )

    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child {
        return when (config) {
            is Config.Details -> {
                val component = defaultDetailsComponent.create(
                    film = config.film,
                    componentContext = componentContext
                )
                RootComponent.Child.Details(component)
            }

            Config.Films -> {
                val component = filmsComponentFactory.create(
                    onFilmItemClicked = {
                        navigation.push(Config.Details(it))
                    },
                    componentContext = componentContext
                )
                RootComponent.Child.Films(component)
            }
        }
    }

    sealed interface Config : Parcelable {

        @Parcelize
        data class Details(val film: Film) : Config

        @Parcelize
        data object Films : Config
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultRootComponent
    }
}