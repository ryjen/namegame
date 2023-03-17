package com.micrantha.namegame.ui.welcome

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import com.micrantha.namegame.ui.MainAction
import com.micrantha.namegame.ui.arch.Action
import com.micrantha.namegame.ui.arch.Dispatcher
import com.micrantha.namegame.ui.components.ViewContext
import com.micrantha.namegame.ui.navi.Route

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
