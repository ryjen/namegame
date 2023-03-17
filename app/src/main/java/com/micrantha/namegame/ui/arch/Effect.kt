package com.micrantha.namegame.ui.arch


typealias Effect<State> = suspend (Action, State) -> Unit
