package com.willowtree.namegame.ui.view

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.willowtree.namegame.R
import com.willowtree.namegame.ui.arch.StoreDispatcher
import com.willowtree.namegame.ui.navi.Route
import org.koin.androidx.compose.get

class ViewContext(
    val context: Context,
    internal val navController: NavHostController,
    private val dispatcher: StoreDispatcher
) : StoreDispatcher by dispatcher {
    fun navigate(route: Route) = navController.navigate(route = route.path)

    fun networkError(@StringRes id: Int = R.string.error_network_general) =
        Toast.makeText(context, id, Toast.LENGTH_LONG).show()
}


@Composable
fun viewContext(): ViewContext {
    val navController = rememberNavController()
    val dispatcher: StoreDispatcher = get()
    val androidContext = LocalContext.current

    val viewContext = remember {
        ViewContext(
            navController = navController,
            dispatcher = dispatcher,
            context = androidContext
        )
    }

    return viewContext
}

