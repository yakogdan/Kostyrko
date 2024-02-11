package com.yakogdan.kinopoiskkostyrko.presentation.popular

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.yakogdan.kinopoiskkostyrko.domain.entity.Film
import com.yakogdan.kinopoiskkostyrko.domain.usecase.ChangeFavouriteStateUseCase
import com.yakogdan.kinopoiskkostyrko.domain.usecase.GetPopularFilmsUseCase
import com.yakogdan.kinopoiskkostyrko.presentation.popular.PopularStore.Intent
import com.yakogdan.kinopoiskkostyrko.presentation.popular.PopularStore.Label
import com.yakogdan.kinopoiskkostyrko.presentation.popular.PopularStore.State
import kotlinx.coroutines.launch
import javax.inject.Inject

interface PopularStore : Store<Intent, State, Label> {

    sealed interface Intent {

        data class CityItemClicked(val film: Film) : Intent

        data class ClickAddToFavourite(val film: Film) : Intent
    }

    data class State(
        val filmsState: FilmsState
    ) {
        sealed interface FilmsState {

            data object Initial : FilmsState

            data object Loading : FilmsState

            data object Error : FilmsState

            data class Loaded(val filmItems: List<Film>) : FilmsState
        }
    }

    sealed interface Label {

        data class CityItemClicked(val film: Film) : Label
    }
}

class PopularStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getPopularFilmsUseCase: GetPopularFilmsUseCase,
    private val changeFavouriteStateUseCase: ChangeFavouriteStateUseCase
) {

    fun create(): PopularStore =
        object : PopularStore, Store<Intent, State, Label> by storeFactory.create(
            name = "PopularStore",
            initialState = State(State.FilmsState.Initial),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {

        data class PopularFilmsLoaded(val films: List<Film>) : Action
        data object PopularFilmsStartLoading : Action
        data object PopularFilmsLoadingError : Action
    }

    private sealed interface Msg {

        data class PopularFilmsLoaded(val films: List<Film>) : Msg

        data object PopularFilmsStartLoading : Msg

        data object PopularFilmsLoadingError : Msg
    }

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                dispatch(Action.PopularFilmsStartLoading)
                try {
                    val films = getPopularFilmsUseCase()
                    dispatch(Action.PopularFilmsLoaded(films))
                } catch (e: Exception) {
                    dispatch(Action.PopularFilmsLoadingError)
                }
            }
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.CityItemClicked -> {
                    publish(Label.CityItemClicked(intent.film))
                }

                is Intent.ClickAddToFavourite -> {
                    scope.launch {
                        changeFavouriteStateUseCase.addToFavourite(intent.film)
                    }
                }
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                is Action.PopularFilmsLoaded -> {
                    dispatch(Msg.PopularFilmsLoaded(action.films))
                }

                Action.PopularFilmsLoadingError -> {
                    dispatch(Msg.PopularFilmsLoadingError)
                }

                Action.PopularFilmsStartLoading -> {
                    dispatch(Msg.PopularFilmsStartLoading)
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State = when (msg) {
            is Msg.PopularFilmsLoaded -> {
                copy(filmsState = State.FilmsState.Loaded(msg.films))
            }

            Msg.PopularFilmsLoadingError -> {
                copy(filmsState = State.FilmsState.Error)
            }

            Msg.PopularFilmsStartLoading -> {
                copy(filmsState = State.FilmsState.Loading)
            }
        }
    }
}