package com.micrantha.namegame.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val picture: Picture,
    val name: Name,
) {
    @Serializable
    data class Picture(
        val large: String? = null,
        val medium: String? = null,
        val thumbnail: String
    )

    @Serializable
    data class Name(
        val first: String,
        val last: String,
    ) {
        override fun toString() = "$first $last"
    }
}
