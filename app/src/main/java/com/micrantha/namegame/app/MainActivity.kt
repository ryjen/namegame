package com.micrantha.namegame.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.micrantha.namegame.data.dataModule
import com.micrantha.namegame.domain.domainModule
import com.micrantha.namegame.ui.MainScreen
import com.micrantha.namegame.ui.components.viewContext
import com.micrantha.namegame.ui.theme.NameGameTheme
import com.micrantha.namegame.ui.uiModule
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            startKoin {
                modules(dataModule, domainModule, uiModule)
            }
        }

        setContent {
            NameGameTheme {
                MainScreen(viewContext(this))
            }
        }
    }
}
