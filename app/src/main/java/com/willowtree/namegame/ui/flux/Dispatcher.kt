package com.willowtree.namegame.ui.flux

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.coroutines.CoroutineContext

typealias Dispatch = (Action) -> Unit

fun interface Dispatcher {
    fun dispatch(action: Action)
}

sealed class DispatchAction : Action {
    object Init : DispatchAction()
}

interface StoreDispatcher : Dispatcher {
    fun <T> createStoreIn(scope: CoroutineScope, state: T): FluxStore<T>
}

class FluxDispatcher(
    private val context: CoroutineContext = Dispatchers.Default
) : StoreDispatcher {
    private val scope = CoroutineScope(context) + Job()
    private val actions = MutableStateFlow<Action>(DispatchAction.Init)

    override fun <T> createStoreIn(scope: CoroutineScope, state: T): FluxStore<T> {
        return FluxStore(scope, state, actions::collect)
    }

    override fun dispatch(action: Action) {
        scope.launch {
            actions.emit(action)
        }
    }
}
