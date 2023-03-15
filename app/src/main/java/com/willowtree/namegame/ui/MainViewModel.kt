package com.willowtree.namegame.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.willowtree.namegame.ui.arch.Action
import com.willowtree.namegame.ui.arch.Dispatcher

class MainViewModel(
    private val uiContext: UiContext
) : ViewModel(), Dispatcher {

    private val store = uiContext.createStoreIn(viewModelScope, MainState())

    init {
        store.addReducer { state, action ->
            when (action) {
                is MainAction.SetTitle -> state.copy(title = action.title)
                else -> state
            }
        }
    }

    fun state() = store.state()

    override fun dispatch(action: Action) = uiContext.dispatch(action)
}
