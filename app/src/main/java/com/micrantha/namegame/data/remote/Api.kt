package com.micrantha.namegame.data.remote

import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class Api(
    engine: HttpClientEngine,
    private val baseUrl: String = "https://randomuser.me/api/1.4",
    private val defaultSeed: String = System.currentTimeMillis().toString(),
    private val defaultSize: Int = 50,
    private val defaultFields: String = "id,name,picture"
) {
    private val client = HttpClient(engine) {
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
            })
        }
    }

    suspend fun profiles(seed: String = defaultSeed, amount: Int = defaultSize) =
        client.request("$baseUrl/?seed=${seed}&results=${amount}&inc=${defaultFields}&noinfo") {
            accept(ContentType.Application.Json)
        }
}
