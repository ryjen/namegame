package com.micrantha.namegame.ui.components

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.micrantha.namegame.R
import com.micrantha.namegame.ui.arch.StoreDispatcher
import com.micrantha.namegame.ui.navi.Route
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.androidx.compose.get

class ViewContext(
    private val context: Context,
    internal val navController: NavHostController,
    private val dispatcher: StoreDispatcher
) : StoreDispatcher by dispatcher {


    fun navigate(route: Route) = navController.navigate(route = route.path) {
        restoreState = true
        launchSingleTop = true
    }

    fun back() = navController.navigateUp()

    suspend fun networkError(@StringRes id: Int = R.string.error_network_general) =
        withContext(Dispatchers.Main) {
            Toast.makeText(context, id, Toast.LENGTH_LONG).show()
        }
}


@Composable
fun viewContext(
    context: Context = LocalContext.current,
    navController: NavHostController = rememberNavController(),
    dispatcher: StoreDispatcher = get()
): ViewContext = remember {
    ViewContext(
        navController = navController,
        dispatcher = dispatcher,
        context = context
    )
}


