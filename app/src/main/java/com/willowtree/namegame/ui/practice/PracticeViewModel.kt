package com.willowtree.namegame.ui.practice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.willowtree.namegame.domain.usecase.RandomEmployeesUseCase
import com.willowtree.namegame.ui.MainAction
import com.willowtree.namegame.ui.UiContext
import com.willowtree.namegame.ui.ViewDispatcher
import com.willowtree.namegame.ui.arch.Store
import kotlinx.coroutines.launch

class PracticeViewModel(
    override val uiContext: UiContext,
    private val randomEmployeesUseCase: RandomEmployeesUseCase
) : ViewModel(), ViewDispatcher {

    private val store: Store<PracticeState> =
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

        dispatch(MainAction.SetTitle("Practice Mode"))

        store.listen {
            print("STATE CHANGE: $it")
        }

        viewModelScope.launch {
            randomEmployeesUseCase(6).onSuccess {
                dispatch(PracticeAction.Loaded(it))
            }
        }
    }

    fun state() = store.state { it.asUiState() }
}

fun PracticeState.asUiState() = PracticeUiState(
    images = randomEmployees.associate { it.employee.id to it.employee.headshot }.entries.toList(),
    name = selectedEmployee?.employee?.name
)
