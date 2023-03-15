package com.willowtree.namegame.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.willowtree.namegame.ui.arch.Action
import com.willowtree.namegame.ui.arch.Dispatcher
import com.willowtree.namegame.ui.arch.StoreDispatcher
import org.koin.androidx.compose.get

class UiContext(
    internal val navController: NavHostController,
    private val dispatcher: StoreDispatcher
) : StoreDispatcher by dispatcher {
    fun navigate(route: Route) = navController.navigate(route = route.path)
}

interface ViewDispatcher : Dispatcher {
    val uiContext: UiContext

    override fun dispatch(action: Action) = uiContext.dispatch(action)
}


@Composable
fun newUiContext(): UiContext {
    val navController = rememberNavController()
    val dispatcher: StoreDispatcher = get()

    val uiContext = remember {
        UiContext(navController = navController, dispatcher = dispatcher)
    }

    return uiContext
}
