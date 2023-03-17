package com.micrantha.namegame.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val id: Id? = null,
    val name: Name? = null,
    val picture: Picture? = null,
) {
    @Serializable
    data class Id(
        val name: String? = null,
        val value: String? = null,
    )

    @Serializable
    data class Picture(
        val large: String? = null,
        val medium: String? = null,
        val thumbnail: String? = null,
    )

    @Serializable
    data class Name(
        val title: String? = null,
        val first: String? = null,
        val last: String? = null
    )
}
