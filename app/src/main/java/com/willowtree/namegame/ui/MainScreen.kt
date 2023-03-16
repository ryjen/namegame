package com.willowtree.namegame.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.willowtree.namegame.ui.navi.Navigation
import com.willowtree.namegame.ui.view.ViewContext
import com.willowtree.namegame.ui.view.viewContext
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
            state.title?.let {
                Text(text = it, style = MaterialTheme.typography.titleMedium)
            }
        }
    ) { contentPadding ->
        Navigation(
            modifier = Modifier.padding(contentPadding),
            viewContext
        )
    }
}
