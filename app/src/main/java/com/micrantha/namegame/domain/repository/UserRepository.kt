package com.micrantha.namegame.domain.repository

import com.micrantha.namegame.domain.model.User

interface UserRepository {
    suspend fun users(): Result<List<User>>
}
