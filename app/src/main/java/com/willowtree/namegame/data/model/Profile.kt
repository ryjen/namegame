package com.willowtree.namegame.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val bio: String? = null,
    val firstName: String,
    val headshot: Headshot,
    val id: String,
    val jobTitle: String? = null,
    val lastName: String,
    val slug: String? = null,
    val socialLinks: List<SocialLink>? = null,
    val type: String? = null
) {
    @Serializable
    data class Headshot(
        val alt: String? = null,
        val height: Int? = null,
        val id: String? = null,
        val mimeType: String? = null,
        val type: String? = null,
        val url: String? = null,
        val width: Int? = null
    )

    @Serializable
    data class SocialLink(
        val callToAction: String? = null,
        val type: String? = null,
        val url: String? = null
    )
}
