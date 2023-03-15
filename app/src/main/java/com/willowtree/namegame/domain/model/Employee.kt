package com.willowtree.namegame.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Employee(
    val headshot: Headshot,
    val name: Name,
    val id: String
) {
    @Serializable
    data class Headshot(
        val alt: String?,
        val url: String
    )

    @Serializable
    data class Name(
        val firstName: String,
        val lastName: String,
    ) {
        override fun toString() = "$firstName $lastName"
    }
}
