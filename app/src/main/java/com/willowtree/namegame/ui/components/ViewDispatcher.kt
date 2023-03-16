package com.willowtree.namegame.ui.components

import com.willowtree.namegame.ui.arch.Action
import com.willowtree.namegame.ui.arch.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

interface ViewDispatcher : Dispatcher {
    val viewContext: ViewContext

    override fun dispatch(action: Action) = viewContext.dispatch(action)
}

fun <State, UiState> StateFlow<State>.mapIn(
    scope: CoroutineScope,
    mapper: (State) -> UiState
): StateFlow<UiState> =
    map(mapper).stateIn(scope, SharingStarted.Eagerly, mapper(value))
