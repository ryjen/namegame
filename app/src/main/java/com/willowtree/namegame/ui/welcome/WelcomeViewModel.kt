package com.willowtree.namegame.ui.welcome

import androidx.lifecycle.ViewModel
import com.willowtree.namegame.ui.arch.Action
import com.willowtree.namegame.ui.arch.Dispatcher
import com.willowtree.namegame.ui.navi.Route
import com.willowtree.namegame.ui.view.ViewContext

class WelcomeViewModel(
    private val viewContext: ViewContext
) : ViewModel(), Dispatcher {

    override fun dispatch(action: Action) = when (action) {
        WelcomeAction.Practice -> viewContext.navigate(Route.Practice)
        else -> Unit
    }
}
