package com.willowtree.namegame.ui.flux

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface Action

typealias Reducer<T> = (state: T, action: Action) -> T

typealias FluxCollector = suspend (FlowCollector<Action>) -> Unit

class FluxStore<State> internal constructor(
    private val scope: CoroutineScope,
    initialState: State,
    private val collector: FluxCollector
) : Dispatcher {
    private val reducers = mutableListOf<Reducer<State>>()
    private val current = MutableStateFlow(initialState)
    private val listeners = MutableStateFlow(initialState)

    init {
        scope.launch {
            collector {
                dispatch(it)
            }
        }
    }

    override fun dispatch(action: Action) {
        val starting = current.value
        current.update { state ->
            reducers.fold(state) { next, reducer -> reducer(next, action) }
        }
        if (starting != current.value) {
            scope.launch {
                listeners.emit(current.value)
            }
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

