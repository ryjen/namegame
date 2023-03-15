package com.willowtree.namegame.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.willowtree.namegame.data.dataModule
import com.willowtree.namegame.domain.domainModule
import com.willowtree.namegame.ui.MainScreen
import com.willowtree.namegame.ui.theme.NameGameTheme
import com.willowtree.namegame.ui.uiModule
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
                MainScreen()
            }
        }
    }
}
