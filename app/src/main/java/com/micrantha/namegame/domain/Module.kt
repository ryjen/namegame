package com.micrantha.namegame.domain

import com.micrantha.namegame.domain.usecase.RandomEmployeesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { RandomEmployeesUseCase(get()) }
}
