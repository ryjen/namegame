package com.willowtree.namegame.ui.flux

import com.willowtree.namegame.ui.arch.Action
import com.willowtree.namegame.ui.arch.Store
import com.willowtree.namegame.ui.arch.StoreDispatcher
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.coroutines.CoroutineContext

class FluxDispatcher(
    context: CoroutineContext = Dispatchers.Default
) : StoreDispatcher {
    private val scope = CoroutineScope(context) + Job()
    private val actions = MutableStateFlow<Action>(FluxAction.Init)

    override fun <T> createStoreIn(scope: CoroutineScope, withState: T): Store<T> {
        return FluxStore(scope, withState).apply {
            // apply all actions from all stores to each other
            register(actions::collect)
        }
    }

    override fun dispatch(action: Action) {
        scope.launch {
            actions.emit(action)
        }
    }
}
