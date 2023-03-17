package com.micrantha.namegame.domain.usecase

import com.micrantha.namegame.domain.model.RandomUser
import com.micrantha.namegame.domain.repository.UserRepository

class RandomEmployeesUseCase(
    private val repository: UserRepository
) {

    suspend operator fun invoke(amount: Int): Result<List<RandomUser>> = try {
        val employees = repository.users().getOrThrow()

        val randomlySelected = employees.shuffled().take(amount)

        val selected = randomlySelected.random().id

        Result.success(randomlySelected.map { RandomUser(it, selected == it.id) })
    } catch (err: Throwable) {
        Result.failure(err)
    }
}
