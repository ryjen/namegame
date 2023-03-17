package com.micrantha.namegame.data.remote.source

import com.micrantha.namegame.data.model.ProfileResponse
import com.micrantha.namegame.data.remote.Api
import io.ktor.client.call.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

class RemoteSource(
    private val api: Api
) {
    @OptIn(ExperimentalSerializationApi::class)
    suspend fun profiles(): Result<ProfileResponse> = try {
        Result.success(Json.decodeFromStream(api.profiles().body()))
    } catch (err: Throwable) {
        Result.failure(err)
    }
}
