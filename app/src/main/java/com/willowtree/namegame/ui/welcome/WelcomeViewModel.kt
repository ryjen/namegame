package com.willowtree.namegame.ui.welcome

import androidx.lifecycle.ViewModel
import com.willowtree.namegame.ui.Route
import com.willowtree.namegame.ui.UiContext
import com.willowtree.namegame.ui.arch.Action
import com.willowtree.namegame.ui.arch.Dispatcher

class WelcomeViewModel(
    private val uiContext: UiContext
) : ViewModel(), Dispatcher {

    override fun dispatch(action: Action) = when (action) {
        WelcomeAction.Practice -> uiContext.navigate(Route.Practice)
        else -> Unit
    }
}
