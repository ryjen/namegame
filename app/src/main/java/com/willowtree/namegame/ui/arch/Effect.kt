package com.willowtree.namegame.ui.arch


typealias Effect<State> = suspend (Action, State) -> Unit
