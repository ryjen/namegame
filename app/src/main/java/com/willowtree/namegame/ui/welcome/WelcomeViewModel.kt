package com.willowtree.namegame.ui.welcome

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import com.willowtree.namegame.ui.MainAction
import com.willowtree.namegame.ui.arch.Action
import com.willowtree.namegame.ui.arch.Dispatcher
import com.willowtree.namegame.ui.components.ViewContext
import com.willowtree.namegame.ui.navi.Route

class WelcomeViewModel(
    private val viewContext: ViewContext
) : ViewModel(), Dispatcher {

    override fun dispatch(action: Action) = when (action) {
        WelcomeAction.Practice -> viewContext.navigate(Route.Practice)
        else -> Unit
    }

    fun onLifecycleEvent(event: Lifecycle.Event) = when (event) {
        Lifecycle.Event.ON_START -> viewContext.dispatch(MainAction.Refresh)
        else -> Unit
    }
}
