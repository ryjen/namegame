package com.micrantha.namegame.ui

import androidx.lifecycle.ViewModel
import com.micrantha.namegame.ui.arch.Action
import com.micrantha.namegame.ui.arch.Dispatcher
import com.micrantha.namegame.ui.components.ViewContext

class MainViewModel(
    private val viewContext: ViewContext
) : ViewModel(), Dispatcher {

    private val store =
        viewContext.createStore(withState = MainState())

    init {
        store.addReducer { state, action ->
            when (action) {
                is MainAction.SetTitle -> state.copy(
                    title = action.title,
                    showBack = action.showBack
                )
                is MainAction.Refresh -> state.copy(
                    title = null,
                    showBack = false
                )
                else -> state
            }
        }
    }

    fun state() = store.state()

    override fun dispatch(action: Action) = viewContext.dispatch(action)

}
