package com.willowtree.namegame.ui

import com.willowtree.namegame.ui.arch.Action

sealed class MainAction : Action {
    data class SetTitle(val title: String) : MainAction()
}

data class MainState(
    val title: String? = null
)
