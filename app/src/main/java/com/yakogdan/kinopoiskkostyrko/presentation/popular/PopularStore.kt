package com.yakogdan.kinopoiskkostyrko.presentation.popular

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.yakogdan.kinopoiskkostyrko.presentation.popular.PopularStore.Intent
import com.yakogdan.kinopoiskkostyrko.presentation.popular.PopularStore.Label
import com.yakogdan.kinopoiskkostyrko.presentation.popular.PopularStore.State

internal interface PopularStore : Store<Intent, State, Label> {

    sealed interface Intent {
    }

    data class State(val todo: Unit)

    sealed interface Label {
    }
}

internal class PopularStoreFactory(
    private val storeFactory: StoreFactory
) {

    fun create(): PopularStore =
        object : PopularStore, Store<Intent, State, Label> by storeFactory.create(
            name = "PopularStore",
            initialState = State(Unit),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
    }

    private sealed interface Msg {
    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
        }
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
        }

        override fun executeAction(action: Action, getState: () -> State) {
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State = State(Unit)
    }
}
