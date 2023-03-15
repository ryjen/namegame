package com.willowtree.namegame.ui.arch

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface Action

typealias Reducer<T> = (state: T, action: Action) -> T

fun interface ReducerStore<State> {
    fun addReducer(reducer: Reducer<State>): Store<State>
}

interface StatefulStore<State> {

    fun state(): StateFlow<State>

    fun <UiState> state(mapper: (State) -> UiState): StateFlow<UiState>
}

interface StoreListener<State> {

    fun listen(block: (State) -> Unit): Store<State>
    fun listenIn(scope: CoroutineScope, block: (State) -> Unit): Store<State>
}

interface StoreRegistrar<State> {

    fun register(collector: DispatchCollector): Store<State>
    fun registerIn(scope: CoroutineScope, collector: DispatchCollector): Store<State>
}

interface Store<State> : StatefulStore<State>, ReducerStore<State>, StoreListener<State>,
    StoreRegistrar<State>
