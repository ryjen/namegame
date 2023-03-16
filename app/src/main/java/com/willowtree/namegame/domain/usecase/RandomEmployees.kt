package com.willowtree.namegame.domain.usecase

import com.willowtree.namegame.domain.model.RandomEmployee
import com.willowtree.namegame.domain.repository.EmployeeRepository

class RandomEmployeesUseCase(
    private val repository: EmployeeRepository
) {

    suspend operator fun invoke(amount: Int): Result<List<RandomEmployee>> = try {
        val employees = repository.employees().getOrThrow()

        val randomlySelected = employees.shuffled().take(amount)

        val selected = randomlySelected.random().id

        Result.success(randomlySelected.map { RandomEmployee(it, selected == it.id) })
    } catch (err: Throwable) {
        Result.failure(err)
    }
}
