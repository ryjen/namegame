package com.micrantha.namegame.data.repository

import com.micrantha.namegame.data.model.Profile
import com.micrantha.namegame.data.model.ProfileResponse
import com.micrantha.namegame.data.remote.source.RemoteSource
import com.micrantha.namegame.domain.model.User
import com.micrantha.namegame.domain.repository.UserRepository

class ProfileRepository(
    private val remoteSource: RemoteSource,
) : UserRepository {
    override suspend fun users() = remoteSource.profiles().map { it.toDomain() }
}

fun ProfileResponse.toDomain(): List<User> = results?.map {
    User(
        id = it.id?.value ?: "",
        name = it.name.toDomain(),
        picture = it.picture.toDomain()
    )
} ?: emptyList()

fun Profile.Name?.toDomain() = User.Name(
    first = this?.first ?: "",
    last = this?.last ?: ""
)

fun Profile.Picture?.toDomain() = User.Picture(
    large = this?.large,
    medium = this?.medium,
    thumbnail = this?.thumbnail ?: ""
)
