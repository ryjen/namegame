package com.micrantha.namegame.ui.navi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.micrantha.namegame.ui.components.ViewContext
import com.micrantha.namegame.ui.practice.PracticeScreen
import com.micrantha.namegame.ui.welcome.WelcomeScreen
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun Navigation(
    modifier: Modifier = Modifier,
    context: ViewContext
) {

    NavHost(
        navController = context.navController,
        startDestination = Route.Welcome.path,
        modifier = modifier
    ) {
        composable("welcome") { WelcomeScreen(screenArgs(context)) }
        composable("practice") { PracticeScreen(screenArgs(context)) }
    }
}

@Composable
private inline fun <reified T : ViewModel> screenArgs(vararg params: Any?) =
    getViewModel<T> { parametersOf(*params) }
