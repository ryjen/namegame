package com.willowtree.namegame.ui

import androidx.lifecycle.ViewModel
import com.willowtree.namegame.ui.arch.Action
import com.willowtree.namegame.ui.arch.Dispatcher
import com.willowtree.namegame.ui.view.ViewContext

class MainViewModel(
    private val viewContext: ViewContext
) : ViewModel(), Dispatcher {

    private val store =
        viewContext.createStoreIn(withState = MainState())

    init {
        store.addReducer { state, action ->
            when (action) {
                is MainAction.SetTitle -> state.copy(title = action.title)
                else -> state
            }
        }
    }

    fun state() = store.state()

    override fun dispatch(action: Action) = viewContext.dispatch(action)
}
