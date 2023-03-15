package com.willowtree.namegame.data.remote.source

import com.willowtree.namegame.data.model.Profile
import com.willowtree.namegame.data.remote.Api
import io.ktor.client.call.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

class RemoteSource(
    private val api: Api
) {
    @OptIn(ExperimentalSerializationApi::class)
    suspend fun employees(): List<Profile> = Json.decodeFromStream(api.profiles().body())
}