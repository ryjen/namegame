package com.willowtree.namegame.data

import com.willowtree.namegame.data.remote.Api
import io.ktor.client.engine.cio.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class TestApiResponse {

    @Test
    fun `api can parse response`() {
        runBlocking {
            val client = Api(CIO.create())
            val response = client.profiles()
            assertEquals(200, response.status.value)
        }
    }
}
