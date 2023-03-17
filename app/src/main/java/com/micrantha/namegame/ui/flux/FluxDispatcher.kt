package com.micrantha.namegame.ui.flux

import com.micrantha.namegame.ui.arch.Action
import com.micrantha.namegame.ui.arch.Store
import com.micrantha.namegame.ui.arch.StoreDispatcher
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class FluxDispatcher(
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default) + Job()
) : StoreDispatcher {
    private val actions = MutableSharedFlow<Action>()

    override fun <T> createStore(withState: T): Store<T> =
        FluxStore(withState).apply {
            actions.onEach { dispatch(it) }.launchIn(scope)
        }

    override fun dispatch(action: Action) {
        scope.launch {
            actions.emit(action)
        }
    }
}
