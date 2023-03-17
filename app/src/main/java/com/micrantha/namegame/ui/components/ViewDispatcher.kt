package com.micrantha.namegame.ui.components

import com.micrantha.namegame.ui.arch.Action
import com.micrantha.namegame.ui.arch.Dispatcher

interface ViewDispatcher : Dispatcher {
    val viewContext: ViewContext

    override fun dispatch(action: Action) = viewContext.dispatch(action)
}
