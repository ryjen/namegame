package com.willowtree.namegame.ui.flux

import com.willowtree.namegame.ui.arch.Action
import com.willowtree.namegame.ui.arch.Store
import com.willowtree.namegame.ui.arch.StoreDispatcher
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

object FluxInit : Action

class FluxDispatcher(
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default) + Job()
) : StoreDispatcher {
    private val actions = MutableSharedFlow<Action>()

    override fun <T> createStoreIn(withState: T): Store<T> =
        FluxStore(withState).apply {
            actions.onEach { dispatch(it) }.launchIn(scope)
        }

    override fun dispatch(action: Action) {
        scope.launch {
            actions.emit(action)
        }
    }
}
