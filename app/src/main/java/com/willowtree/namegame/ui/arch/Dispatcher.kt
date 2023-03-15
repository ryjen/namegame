package com.willowtree.namegame.ui.arch

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.coroutines.CoroutineContext

typealias Dispatch = (Action) -> Unit

typealias DispatchCollector = suspend (FlowCollector<Action>) -> Unit

fun interface Dispatcher {
    fun dispatch(action: Action)
}

interface StoreDispatcher : Dispatcher {
    fun <T> createStoreIn(scope: CoroutineScope, withState: T): Store<T>
}
