package com.willowtree.namegame.ui.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.willowtree.namegame.ui.arch.StoreDispatcher
import com.willowtree.namegame.ui.navi.Route
import org.koin.androidx.compose.get

class ViewContext(
    internal val navController: NavHostController,
    private val dispatcher: StoreDispatcher
) : StoreDispatcher by dispatcher {
    fun navigate(route: Route) = navController.navigate(route = route.path)
}


@Composable
fun viewContext(): ViewContext {
    val navController = rememberNavController()
    val dispatcher: StoreDispatcher = get()

    val context = remember {
        ViewContext(navController = navController, dispatcher = dispatcher)
    }

    return context
}

