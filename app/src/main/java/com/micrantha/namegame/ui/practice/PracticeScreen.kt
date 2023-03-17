package com.micrantha.namegame.ui.practice

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.micrantha.namegame.R
import com.micrantha.namegame.domain.model.User
import com.micrantha.namegame.ui.arch.Dispatch

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

        item(span = { GridItemSpan(2) }) {
            Text(
                text = state.name,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }

        items(state.users) { user ->
            UserHeadshot(user, state.selectedId == user.id) {
                dispatch(PracticeAction.Guess(user.id))
            }
        }
    }
}

@Composable
private fun UserHeadshot(
    user: User,
    selected: Boolean,
    size: Dp = 200.dp,
    onClick: () -> Unit
) =
    Surface(
        modifier = Modifier
            .sizeIn(size, size)
            .padding(6.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .clickable(onClick = onClick),
            model = user.picture.medium,
            alignment = Alignment.Center,
            contentScale = ContentScale.Fit,
            contentDescription = user.name.toString()
        )
        if (selected) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Green.copy(alpha = 0.5f)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    painter = painterResource(id = R.drawable.check_star),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null
                )
            }
        }
    }


@Preview(showBackground = true)
@Composable
private fun PracticePreview() {
    PracticeContent(
        state = PracticeUiState(
            users = List(3) {
                User(
                    id = "${it + 1}",
                    picture = User.Picture(
                        thumbnail = "https://random.imagecdn.app/v1/image?width=500&height=150",
                    ),
                    name = User.Name("Test", "Testerson")
                )
            },
            selectedId = "2",
            name = "Test Testerson"
        )
    ) {}
}
