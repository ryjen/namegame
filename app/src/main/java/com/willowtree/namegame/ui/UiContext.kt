package com.willowtree.namegame.ui

import androidx.navigation.NavController
import com.willowtree.namegame.ui.flux.StoreDispatcher

class UiContext(
    private val navController: NavController,
    private val dispatcher: StoreDispatcher
) : StoreDispatcher by dispatcher {
    fun navigate(route: Route) = navController.navigate(route = route.path)
}
