package com.willowtree.namegame.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.willowtree.namegame.ui.components.ViewContext
import com.willowtree.namegame.ui.components.viewContext
import com.willowtree.namegame.ui.navi.Navigation
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewContext: ViewContext = viewContext(),
    viewModel: MainViewModel = getViewModel { parametersOf(viewContext) }
) {
    val state by viewModel.state().collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    state.title?.let { MainTitle(it) }
                },
                navigationIcon = {
                    if (state.showBack) {
                        MainBackButton(state, viewContext::back)
                    }
                }
            )
        }
    ) { contentPadding ->
        Navigation(
            modifier = Modifier.padding(contentPadding),
            viewContext
        )
    }
}

@Composable
private fun MainTitle(title: String) =
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium.copy(
            color = Color.White
        )
    )

@Composable
private fun MainBackButton(state: MainState, onBack: () -> Unit) =
    IconButton(onClick = {
        state.onBack?.let { it() } ?: onBack()
    }) {
        Icon(
            Icons.Default.KeyboardArrowLeft,
            contentDescription = null,
            tint = Color.White
        )
    }

