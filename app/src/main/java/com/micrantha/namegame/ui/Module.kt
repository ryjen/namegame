package com.micrantha.namegame.ui

import com.micrantha.namegame.ui.arch.StoreDispatcher
import com.micrantha.namegame.ui.flux.FluxDispatcher
import com.micrantha.namegame.ui.practice.PracticeViewModel
import com.micrantha.namegame.ui.welcome.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::WelcomeViewModel)

    viewModel { param -> PracticeViewModel(viewContext = param.get(), get()) }

    viewModelOf(::MainViewModel)

    single<StoreDispatcher> { FluxDispatcher() }
}
