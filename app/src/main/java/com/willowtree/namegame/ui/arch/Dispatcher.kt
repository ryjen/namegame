package com.willowtree.namegame.ui.arch

typealias Dispatch = (Action) -> Unit

fun interface Dispatcher {
    fun dispatch(action: Action)
}

interface StoreDispatcher : Dispatcher {
    fun <T> createStoreIn(withState: T): Store<T>
}
