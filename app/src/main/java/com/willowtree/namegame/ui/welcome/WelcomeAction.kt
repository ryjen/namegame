package com.willowtree.namegame.ui.welcome

import com.willowtree.namegame.ui.flux.Action

sealed class WelcomeAction : Action {
    object Practice : WelcomeAction()
    object Timed : WelcomeAction()
}
