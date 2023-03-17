package com.micrantha.namegame.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    val results: List<Profile>? = null
)
