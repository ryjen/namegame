package com.micrantha.namegame.ui.practice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.micrantha.namegame.domain.usecase.RandomEmployeesUseCase
import com.micrantha.namegame.ui.MainAction
import com.micrantha.namegame.ui.arch.StatefulStore
import com.micrantha.namegame.ui.arch.Store
import com.micrantha.namegame.ui.components.ViewContext
import com.micrantha.namegame.ui.components.ViewDispatcher
import com.micrantha.namegame.ui.components.mapIn
import kotlinx.coroutines.launch

class PracticeViewModel(
    override val viewContext: ViewContext,
    private val randomEmployeesUseCase: RandomEmployeesUseCase,
) : ViewModel(), ViewDispatcher, StatefulStore<PracticeUiState> {

    private val store: Store<PracticeState> =
        viewContext.createStore(withState = PracticeState())

    init {
        store.setup()

        dispatch(MainAction.setTitle {
            title = "Practice Mode"
            showBack = true
        })

        viewModelScope.launch {
            randomEmployeesUseCase(6).onSuccess {
                dispatch(PracticeAction.Loaded(it))
            }.onFailure {
                dispatch(PracticeAction.LoadError(it))
            }
        }
    }

    private fun Store<PracticeState>.setup() {
        addReducer { state, action ->
            when (action) {
                is PracticeAction.Loaded -> state.copy(
                    randomUsers = action.users,
                    selectedUser = action.users.find { it.selected }
                )
                else -> state
            }
        }.applyEffect { action, _ ->
            when (action) {
                is PracticeAction.LoadError -> viewContext.networkError()
            }
        }
    }

    override fun state() = store.state().mapIn(viewModelScope) { it.asUiState() }

}

fun PracticeState.asUiState() = PracticeUiState(
    users = randomUsers.map { it.user },
    selectedId = selectedUser?.user?.id ?: "",
    name = selectedUser?.user?.name?.toString() ?: ""
)
