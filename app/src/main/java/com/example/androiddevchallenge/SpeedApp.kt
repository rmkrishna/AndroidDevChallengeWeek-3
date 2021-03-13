package com.example.androiddevchallenge

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.Composable
import com.example.androiddevchallenge.ui.theme.MyTheme
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import com.example.androiddevchallenge.ui.LoginScreen
import com.example.androiddevchallenge.ui.MainScreen
import com.example.androiddevchallenge.ui.WelcomeScreen
import com.example.androiddevchallenge.util.Navigator
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets


@Composable
fun SpeedApp(backDispatcher: OnBackPressedDispatcher) {

    val navigator: Navigator<Destination> = rememberSaveable(
        saver = Navigator.saver(backDispatcher)
    ) {
        Navigator(Destination.Welcome, backDispatcher)
    }
    val actions = remember(navigator) { Actions(navigator) }

    ProvideWindowInsets {
        MyTheme {
            Crossfade(navigator.current) { destination ->
                when (destination) {
                    Destination.Welcome -> WelcomeScreen {
                        actions.moveTo(Destination.Login)
                    }
                    Destination.Login -> LoginScreen {
                        actions.moveTo(Destination.Main)
                    }
                    Destination.Main -> MainScreen()
                }
            }
        }
    }
}
