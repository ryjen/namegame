package com.willowtree.namegame.ui.practice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.willowtree.namegame.domain.usecase.RandomEmployeesUseCase
import com.willowtree.namegame.ui.UiContext
import com.willowtree.namegame.ui.flux.Action
import com.willowtree.namegame.ui.flux.Dispatcher
import com.willowtree.namegame.ui.flux.FluxStore
import kotlinx.coroutines.launch

class PracticeViewModel(
    uiContext: UiContext,
    private val randomEmployeesUseCase: RandomEmployeesUseCase
) : ViewModel(), Dispatcher {

    private val store: FluxStore<PracticeState> =
        uiContext.createStoreIn(viewModelScope, PracticeState())

    init {
        store.addReducer { state, action ->
            when (action) {
                is PracticeAction.Loaded -> state.copy(
                    randomEmployees = action.employees,
                    selectedEmployee = action.employees.find { it.selected }
                )
                else -> state
            }
        }

        viewModelScope.launch {
            store.listener {
                print("STATE CHANGE: $it")
            }
        }

        viewModelScope.launch {
            randomEmployeesUseCase(6).onSuccess {
                dispatch(PracticeAction.Loaded(it))
            }
        }
    }

    fun state() = store.stateIn(viewModelScope) { it.asUiState() }

    override fun dispatch(action: Action) = store.dispatch(action)
}

fun PracticeState.asUiState() = PracticeUiState(
    images = randomEmployees.associate { it.employee.id to it.employee.headshot }.entries.toList(),
    name = selectedEmployee?.employee?.name
)
