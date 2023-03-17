package com.micrantha.namegame.ui.practice

import com.micrantha.namegame.domain.model.RandomUser
import com.micrantha.namegame.domain.model.User
import com.micrantha.namegame.ui.arch.Action

sealed class PracticeAction : Action {
    data class Loaded(val users: List<RandomUser>) : PracticeAction()
    data class Guess(val id: String) : PracticeAction()

    data class LoadError(val err: Throwable) : PracticeAction()
}

data class PracticeState(
    val randomUsers: List<RandomUser> = emptyList(),
    val selectedUser: RandomUser? = null
)

data class PracticeUiState(
    val users: List<User>,
    val selectedId: String,
    val name: String
)
