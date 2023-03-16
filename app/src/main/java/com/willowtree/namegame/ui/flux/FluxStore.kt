package com.willowtree.namegame.ui.flux

import com.willowtree.namegame.ui.arch.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.plus

class FluxStore<State> internal constructor(
    initialState: State,
    private val listenScope: CoroutineScope = CoroutineScope(Dispatchers.Unconfined) + Job()
) : Store<State>, Dispatcher, StoreListener<State> {
    private val reducers = mutableListOf<Reducer<State>>()
    private val current = MutableStateFlow(initialState)

    override fun dispatch(action: Action) {
        current.update { state ->
            reducers.fold(state) { next, reducer -> reducer(next, action) }
        }
    }

    override fun addReducer(reducer: Reducer<State>): FluxStore<State> {
        reducers.add(reducer)
        return this
    }

    override fun state(): StateFlow<State> = current.asStateFlow()
    
    override fun listen(block: (State) -> Unit): Store<State> {
        current.onEach { block(it) }.launchIn(listenScope)
        return this
    }
}

