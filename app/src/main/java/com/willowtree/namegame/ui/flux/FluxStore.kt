package com.willowtree.namegame.ui.flux

import com.willowtree.namegame.ui.arch.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FluxStore<State> internal constructor(
    private val scope: CoroutineScope,
    initialState: State,
) : Store<State>, Dispatcher {
    private val reducers = mutableListOf<Reducer<State>>()
    private val current = MutableStateFlow(initialState)

    override fun listen(block: (State) -> Unit) = listenIn(scope, block)

    override fun listenIn(scope: CoroutineScope, block: (State) -> Unit): FluxStore<State> {
        scope.launch { current.collect { block(it) } }
        return this
    }

    override fun register(collector: DispatchCollector) = registerIn(scope, collector)

    override fun registerIn(scope: CoroutineScope, collector: DispatchCollector): FluxStore<State> {
        scope.launch { collector { dispatch(it) } }
        return this
    }

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

    override fun <UiState> state(mapper: (State) -> UiState) =
        current.map(mapper).stateIn(scope, SharingStarted.Eagerly, mapper(current.value))
}

