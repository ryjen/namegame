package com.willowtree.namegame.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.willowtree.namegame.ui.flux.StoreDispatcher
import org.koin.androidx.compose.get

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val dispatcher: StoreDispatcher = get()

    val uiContext = remember {
        UiContext(navController = navController, dispatcher = dispatcher)
    }

    Scaffold(
        topBar = {

        }
    ) { innerPadding ->
        Navigation(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            uiContext
        )
    }
}
