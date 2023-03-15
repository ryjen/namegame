package com.willowtree.namegame.ui.welcome

import androidx.lifecycle.ViewModel
import com.willowtree.namegame.ui.Route
import com.willowtree.namegame.ui.UiContext
import com.willowtree.namegame.ui.flux.Action
import com.willowtree.namegame.ui.flux.Dispatcher

class WelcomeViewModel(
    private val uiContext: UiContext
) : ViewModel(), Dispatcher {

    override fun dispatch(action: Action) = when (action) {
        WelcomeAction.Practice -> uiContext.navigate(Route.Practice)
        else -> Unit
    }
}
