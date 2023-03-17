package com.micrantha.namegame.ui.arch

import kotlinx.coroutines.flow.StateFlow

interface Action

typealias Reducer<T> = (state: T, action: Action) -> T

fun interface ReducerStore<State> {
    fun addReducer(reducer: Reducer<State>): Store<State>
}

interface StatefulStore<State> {

    fun state(): StateFlow<State>
}

interface EffectedStore<State> {
    fun applyEffect(effect: Effect<State>): Store<State>
}

interface StoreListener<State> {

    fun listen(block: (State) -> Unit): Store<State>
}

interface Store<State> : StatefulStore<State>, ReducerStore<State>, EffectedStore<State>
