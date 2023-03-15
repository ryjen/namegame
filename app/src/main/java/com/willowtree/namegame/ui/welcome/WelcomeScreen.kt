package com.willowtree.namegame.ui.welcome

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.willowtree.namegame.R
import com.willowtree.namegame.ui.flux.Dispatch
import com.willowtree.namegame.ui.theme.Blue100
import com.willowtree.namegame.ui.theme.Blue200

@Composable
fun WelcomeScreen(viewModel: WelcomeViewModel) {
    WelcomeContent(viewModel::dispatch)
}

@Composable
private fun WelcomeContent(dispatch: Dispatch) {
    val orientation = LocalConfiguration.current.orientation

    Surface(Modifier.fillMaxSize(), color = Blue200) {
        Image(
            painter = painterResource(
                id = if (orientation == Configuration.ORIENTATION_LANDSCAPE)
                    R.drawable.logo_horizontal
                else
                    R.drawable.logo_vertical
            ),
            alignment = Alignment.CenterStart,
            contentScale = ContentScale.Fit,
            contentDescription = null
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .padding(top = 600.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Try matching the WillowTree Employee to their photo",
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Blue100),
                modifier = Modifier.fillMaxWidth(),
                onClick = { dispatch(WelcomeAction.Practice) }) {
                Text("Practice Mode")
            }

            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Blue100),
                modifier = Modifier.fillMaxWidth(), onClick = { dispatch(WelcomeAction.Timed) }) {
                Text("Timed Mode")
            }
        }
    }
}

@Preview
@Composable
private fun MainPreview() {
    WelcomeContent(dispatch = {})
}
