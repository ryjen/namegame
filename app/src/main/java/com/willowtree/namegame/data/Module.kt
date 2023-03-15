package com.willowtree.namegame.data

import com.willowtree.namegame.data.remote.Api
import com.willowtree.namegame.data.remote.source.RemoteSource
import com.willowtree.namegame.data.repository.ProfileRepository
import com.willowtree.namegame.domain.repository.EmployeeRepository
import io.ktor.client.engine.cio.*
import org.koin.dsl.module

val dataModule = module {

    single {
        Api(CIO.create())
    }

    factory { RemoteSource(get()) }

    single<EmployeeRepository> { ProfileRepository(get()) }
}
