package com.willowtree.namegame.ui.practice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.willowtree.namegame.domain.usecase.RandomEmployeesUseCase
import com.willowtree.namegame.ui.MainAction
import com.willowtree.namegame.ui.arch.StatefulStore
import com.willowtree.namegame.ui.arch.Store
import com.willowtree.namegame.ui.view.ViewContext
import com.willowtree.namegame.ui.view.ViewDispatcher
import com.willowtree.namegame.ui.view.mapIn
import kotlinx.coroutines.launch

class PracticeViewModel(
    override val viewContext: ViewContext,
    private val randomEmployeesUseCase: RandomEmployeesUseCase,
) : ViewModel(), ViewDispatcher, StatefulStore<PracticeUiState> {

    private val store: Store<PracticeState> =
        viewContext.createStore(withState = PracticeState())

    init {
        store.addReducer { state, action ->
            when (action) {
                is PracticeAction.Loaded -> state.copy(
                    randomEmployees = action.employees,
                    selectedEmployee = action.employees.find { it.selected }
                )
                else -> state
            }
        }.applyEffect { action, _ ->
            when (action) {
                is PracticeAction.LoadError -> viewContext.networkError()
            }
        }

        dispatch(MainAction.SetTitle("Practice Mode"))

        viewModelScope.launch {
            randomEmployeesUseCase(6).onSuccess {
                dispatch(PracticeAction.Loaded(it))
            }.onFailure {
                dispatch(PracticeAction.LoadError(it))
            }
        }
    }

    override fun state() = store.state().mapIn(viewModelScope) { it.asUiState() }

}

fun PracticeState.asUiState() = PracticeUiState(
    images = randomEmployees.associate { it.employee.id to it.employee.headshot }.entries.toList(),
    name = selectedEmployee?.employee?.name
)
