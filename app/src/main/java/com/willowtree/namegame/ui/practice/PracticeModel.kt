package com.willowtree.namegame.ui.practice

import com.willowtree.namegame.domain.model.Employee
import com.willowtree.namegame.domain.model.RandomEmployee
import com.willowtree.namegame.ui.arch.Action

sealed class PracticeAction : Action {
    data class Loaded(val employees: List<RandomEmployee>) : PracticeAction()
    data class Guess(val id: String) : PracticeAction()
}

data class PracticeState(
    val randomEmployees: List<RandomEmployee> = emptyList(),
    val selectedEmployee: RandomEmployee? = null
)

data class PracticeUiState(
    val images: List<Map.Entry<String, Employee.Headshot>>,
    val name: Employee.Name?
)
