package com.willowtree.namegame.ui

import com.willowtree.namegame.ui.flux.FluxDispatcher
import com.willowtree.namegame.ui.flux.StoreDispatcher
import com.willowtree.namegame.ui.practice.PracticeViewModel
import com.willowtree.namegame.ui.welcome.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val uiModule = module {
    viewModelOf(::WelcomeViewModel)

    viewModel { param -> PracticeViewModel(uiContext = param.get(), get()) }

    single<StoreDispatcher> { FluxDispatcher() }
}
