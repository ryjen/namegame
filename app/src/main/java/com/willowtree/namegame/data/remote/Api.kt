package com.willowtree.namegame.data.remote

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class Api(
    engine: HttpClientEngine,
    private val baseUrl: String = "https://namegame.willowtreeapps.com/api/v1.0"
) {
    private val client = HttpClient(engine) {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
            })
        }
    }

    suspend fun profiles() = client.request("$baseUrl/profiles") {
        accept(ContentType.Application.Json)
    }
}
