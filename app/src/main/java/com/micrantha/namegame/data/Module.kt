package com.micrantha.namegame.data

import com.micrantha.namegame.data.remote.Api
import com.micrantha.namegame.data.remote.source.RemoteSource
import com.micrantha.namegame.data.repository.ProfileRepository
import com.micrantha.namegame.domain.repository.UserRepository
import io.ktor.client.engine.cio.*
import org.koin.dsl.module

val dataModule = module {

    single {
        Api(CIO.create())
    }

    factory { RemoteSource(get()) }

    single<UserRepository> { ProfileRepository(get()) }
}
