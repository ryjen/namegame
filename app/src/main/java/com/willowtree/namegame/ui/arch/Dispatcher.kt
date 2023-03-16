package com.willowtree.namegame.ui.arch

typealias Dispatch = (Action) -> Unit

fun interface Dispatcher {
    fun dispatch(action: Action)
}

interface StoreDispatcher : Dispatcher {
    fun <T> createStore(withState: T): Store<T>
}
