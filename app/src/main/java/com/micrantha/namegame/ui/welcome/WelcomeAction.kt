package com.micrantha.namegame.ui.welcome

import com.micrantha.namegame.ui.arch.Action

sealed class WelcomeAction : Action {
    object Practice : WelcomeAction()
    object Timed : WelcomeAction()
}
