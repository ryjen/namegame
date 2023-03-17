package com.micrantha.namegame.ui.flux

import com.micrantha.namegame.ui.arch.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class FluxStore<State> internal constructor(
    initialState: State,
    private val stateScope: CoroutineScope = CoroutineScope(Dispatchers.Unconfined) + Job()
) : Store<State>, Dispatcher, StoreListener<State> {
    private val reducers = mutableListOf<Reducer<State>>()
    private val effects = mutableListOf<Effect<State>>()
    private val current = MutableStateFlow(initialState)

    override fun dispatch(action: Action) {
        current.update { state ->
            reducers.fold(state) { next, reducer -> reducer(next, action) }
        }
        stateScope.launch {
            effects.forEach { it(action, current.value) }
        }
    }

    override fun addReducer(reducer: Reducer<State>): FluxStore<State> {
        reducers.add(reducer)
        return this
    }

    override fun applyEffect(effect: Effect<State>): FluxStore<State> {
        effects.add(effect)
        return this
    }

    override fun state(): StateFlow<State> = current.asStateFlow()


    override fun listen(block: (State) -> Unit): Store<State> {
        current.onEach { block(it) }.launchIn(stateScope)
        return this
    }
}

