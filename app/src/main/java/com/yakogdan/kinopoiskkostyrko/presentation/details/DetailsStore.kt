package com.yakogdan.kinopoiskkostyrko.presentation.details

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.yakogdan.kinopoiskkostyrko.domain.entity.Film
import com.yakogdan.kinopoiskkostyrko.presentation.details.DetailsStore.Intent
import com.yakogdan.kinopoiskkostyrko.presentation.details.DetailsStore.Label
import com.yakogdan.kinopoiskkostyrko.presentation.details.DetailsStore.State
import javax.inject.Inject

interface DetailsStore : Store<Intent, State, Label> {

    sealed interface Intent {

        data object ClickBack : Intent
    }

    data class State(val detailsState: DetailsState) {
        sealed interface DetailsState {

            data object Initial : DetailsState

            data object Loading : DetailsState

            data object Error : DetailsState

            data class Loaded(val film: Film) : DetailsState
        }
    }

    sealed interface Label {

        data object ClickBack : Label
    }
}

class DetailsStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory
) {

    fun create(film: Film): DetailsStore =
        object : DetailsStore, Store<Intent, State, Label> by storeFactory.create(
            name = "DetailsStore",
            initialState = State(State.DetailsState.Initial),
            bootstrapper = BootstrapperImpl(film),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data class DetailsLoaded(val film: Film) : Action

        data object DetailsStartLoading : Action

        data object DetailsLoadingError : Action
    }

    private sealed interface Msg {

        data class DetailsLoaded(val film: Film) : Msg

        data object DetailsStartLoading : Msg

        data object DetailsLoadingError : Msg
    }

    private class BootstrapperImpl(private val film: Film) : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            dispatch(Action.DetailsLoaded(film))
        }
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                Intent.ClickBack -> {
                    publish(Label.ClickBack)
                }
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                is Action.DetailsLoaded -> {
                    dispatch(Msg.DetailsLoaded(action.film))
                }

                Action.DetailsLoadingError -> {
                    dispatch(Msg.DetailsLoadingError)
                }

                Action.DetailsStartLoading -> {
                    dispatch(Msg.DetailsStartLoading)
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
            when (msg) {
                is Msg.DetailsLoaded -> {
                    copy(detailsState = State.DetailsState.Loaded(msg.film))
                }

                Msg.DetailsLoadingError -> {
                    copy(detailsState = State.DetailsState.Error)
                }

                Msg.DetailsStartLoading -> {
                    copy(detailsState = State.DetailsState.Loading)
                }
            }
    }
}

