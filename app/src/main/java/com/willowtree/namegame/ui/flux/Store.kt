package com.willowtree.namegame.ui.flux

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface Action

typealias Reducer<T> = (state: T, action: Action) -> T

typealias StoreCollector<T> = suspend (FlowCollector<T>) -> Unit

class FluxStore<State> internal constructor(
    scope: CoroutineScope,
    initialState: State,
    private val collector: DispatchCollector
) : Dispatcher {
    private val reducers = mutableListOf<Reducer<State>>()
    private val current = MutableStateFlow(initialState)

    val listener: StoreCollector<State> = current::collect

    init {
        scope.launch {
            collector {
                dispatch(it)
            }
        }
    }

    override fun dispatch(action: Action) {
        current.update { state ->
            reducers.fold(state) { next, reducer -> reducer(next, action) }
        }
    }

    fun addReducer(reducer: Reducer<State>): FluxStore<State> {
        reducers.add(reducer)
        return this
    }

    fun state(): StateFlow<State> = current.asStateFlow()

    fun <UiState> stateIn(scope: CoroutineScope, mapper: (State) -> UiState) =
        current.map(mapper).stateIn(scope, SharingStarted.Eagerly, mapper(current.value))
}

