package com.yakogdan.kinopoiskkostyrko.presentation.favourite

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.yakogdan.kinopoiskkostyrko.domain.entity.Film
import com.yakogdan.kinopoiskkostyrko.domain.usecase.GetFavouriteFilmsUseCase
import com.yakogdan.kinopoiskkostyrko.presentation.favourite.FavouriteStore.Intent
import com.yakogdan.kinopoiskkostyrko.presentation.favourite.FavouriteStore.Label
import com.yakogdan.kinopoiskkostyrko.presentation.favourite.FavouriteStore.State
import kotlinx.coroutines.launch
import javax.inject.Inject

internal interface FavouriteStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data class CityItemClicked(val film: Film) : Intent
    }

    data class State(
        val filmItems: List<Film>
    )

    sealed interface Label {
        data class CityItemClicked(val film: Film) : Label
    }
}

internal class FavouriteStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getFavouriteFilmsUseCase: GetFavouriteFilmsUseCase
) {

    fun create(): FavouriteStore =
        object : FavouriteStore, Store<Intent, State, Label> by storeFactory.create(
            name = "FavouriteStore",
            initialState = State(listOf()),
            bootstrapper = BootstrapperImpl(),
            executorFactory = FavouriteStoreFactory::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {

        data class FavouriteFilmsLoaded(val films: List<Film>) : Action
    }

    private sealed interface Msg {

        data class FavouriteFilmsLoaded(val films: List<Film>) : Msg
    }

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                getFavouriteFilmsUseCase().collect {
                    dispatch(Action.FavouriteFilmsLoaded(it))
                }
            }
        }
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.CityItemClicked -> {
                    publish(Label.CityItemClicked(intent.film))
                }
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                is Action.FavouriteFilmsLoaded -> {
                    val films = action.films
                    dispatch(Msg.FavouriteFilmsLoaded(films))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State = when (msg) {
            is Msg.FavouriteFilmsLoaded -> {
                copy(filmItems = msg.films)
            }
        }
    }
}
