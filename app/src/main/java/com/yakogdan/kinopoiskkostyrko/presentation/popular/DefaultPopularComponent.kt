package com.yakogdan.kinopoiskkostyrko.presentation.popular

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.yakogdan.kinopoiskkostyrko.domain.entity.Film
import com.yakogdan.kinopoiskkostyrko.presentation.extensions.componentScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DefaultPopularComponent @AssistedInject constructor(
    private val storeFactory: PopularStoreFactory,
    @Assisted("onFilmItemClicked") private val onFilmItemClicked: (Film) -> Unit,
    @Assisted("componentContext") componentContext: ComponentContext
) : PopularComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create() }
    private val scope = componentScope()

    init {
        scope.launch {
            store.labels.collect {
                when (it) {
                    is PopularStore.Label.CityItemClicked -> {
                        onFilmItemClicked(it.film)
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<PopularStore.State> = store.stateFlow

    override fun onFilmItemClick(film: Film) {
        store.accept(PopularStore.Intent.CityItemClicked(film))
    }
}