package com.willowtree.namegame.ui.flux

import com.willowtree.namegame.ui.arch.Action


sealed class FluxAction : Action {
    object Init : FluxAction()
}
