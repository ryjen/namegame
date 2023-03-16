package com.willowtree.namegame.ui.practice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.willowtree.namegame.domain.usecase.RandomEmployeesUseCase
import com.willowtree.namegame.ui.MainAction
import com.willowtree.namegame.ui.arch.StatefulStore
import com.willowtree.namegame.ui.arch.Store
import com.willowtree.namegame.ui.components.ViewContext
import com.willowtree.namegame.ui.components.ViewDispatcher
import com.willowtree.namegame.ui.components.mapIn
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
    }

    override fun state() = store.state().mapIn(viewModelScope) { it.asUiState() }

}

fun PracticeState.asUiState() = PracticeUiState(
    employees = randomEmployees.map { it.employee },
    selectedId = selectedEmployee?.employee?.id ?: "",
    name = selectedEmployee?.employee?.name?.toString() ?: ""
)
