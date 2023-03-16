package com.willowtree.namegame.ui.components

import com.willowtree.namegame.ui.arch.Action
import com.willowtree.namegame.ui.arch.Dispatcher

interface ViewDispatcher : Dispatcher {
    val viewContext: ViewContext

    override fun dispatch(action: Action) = viewContext.dispatch(action)
}
