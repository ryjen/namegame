package com.willowtree.namegame.ui.practice

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.willowtree.namegame.domain.model.Employee
import com.willowtree.namegame.ui.flux.Dispatch

@Composable
fun PracticeScreen(viewModel: PracticeViewModel) {

    val state by viewModel.state().collectAsState()

    PracticeContent(state, viewModel::dispatch)
}

@Composable
private fun PracticeContent(state: PracticeUiState, dispatch: Dispatch) {

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center
    ) {

        state.name?.let {
            item(span = { GridItemSpan(2) }) {
                Text(
                    text = "$it",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }

        items(state.images) { (id, image) ->
            AsyncImage(
                modifier = Modifier
                    .clickable { dispatch(PracticeAction.Guess(id)) }
                    .heightIn(200.dp)
                    .widthIn(200.dp)
                    .padding(6.dp),
                model = image.url,
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit,
                contentDescription = image.alt
            )
        }
    }
}

@Preview
@Composable
private fun PracticePreview() {
    PracticeContent(
        state = PracticeUiState(
            images = mapOf(
                "abc" to Employee.Headshot(
                    url = "https://random.imagecdn.app/v1/image?width=500&height=150",
                    alt = "test image"
                )
            ).entries.toList(),
            name = Employee.Name("Test", "Testerson")
        )
    ) {}
}
