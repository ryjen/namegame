package com.willowtree.namegame.domain

import com.willowtree.namegame.domain.usecase.RandomEmployeesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { RandomEmployeesUseCase(get()) }
}
