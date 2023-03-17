package com.micrantha.namegame.ui.welcome

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.micrantha.namegame.R
import com.micrantha.namegame.ui.arch.Dispatch
import com.micrantha.namegame.ui.components.OnLifecycleEvent

@Composable
fun WelcomeScreen(viewModel: WelcomeViewModel) {

    OnLifecycleEvent(onEvent = viewModel::onLifecycleEvent)

    when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_LANDSCAPE ->
            WelcomeContentLandscape(viewModel::dispatch)
        else -> WelcomeContentPortrait(viewModel::dispatch)
    }

}

@Composable
private fun WelcomeContentPortrait(dispatch: Dispatch) {

    Surface(Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.padding(top = 30.dp),
            painter = painterResource(R.drawable.namegame_dark),
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.Fit,
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .padding(top = 250.dp),
            verticalArrangement = Arrangement.Center
        ) {
            WelcomeActions(dispatch = dispatch)
        }
    }
}


@Composable
private fun WelcomeContentLandscape(dispatch: Dispatch) {

    Surface(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.namegame_dark),
            alignment = Alignment.TopStart,
            contentScale = ContentScale.Fit,
            contentDescription = null
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .padding(start = 400.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                WelcomeActions(dispatch = dispatch)
            }
        }
    }
}

@Composable
private fun WelcomeActions(dispatch: Dispatch) {
    Text(
        text = "Try matching the user to their photo",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.heightIn(10.dp))

    TextButton(
        colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.onSurface),
        modifier = Modifier.fillMaxWidth(),
        onClick = { dispatch(WelcomeAction.Practice) }) {
        Text("Practice Mode", style = MaterialTheme.typography.titleLarge)
    }

    TextButton(
        colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.onSurface),
        modifier = Modifier.fillMaxWidth(),
        onClick = { dispatch(WelcomeAction.Timed) }) {
        Text("Timed Mode", style = MaterialTheme.typography.titleLarge)
    }
}

@Preview
@Composable
private fun MainPreview() {
    WelcomeContentPortrait(dispatch = {})
}
