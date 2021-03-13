package com.example.androiddevchallenge

import android.os.Parcelable
import com.example.androiddevchallenge.util.Navigator
import kotlinx.parcelize.Parcelize

/**
 * Models the screens in the app and any arguments they require.
 */
sealed class Destination : Parcelable {
    @Parcelize
    object Welcome : Destination()

    @Parcelize
    object Login : Destination()

    @Parcelize
    object Main : Destination()
}

/**
 * Models the navigation actions in the app.
 */
class Actions(navigator: Navigator<Destination>) {
    val moveTo: (Destination) -> Unit = { destination: Destination ->
        navigator.navigate(destination)
    }
    val upPress: () -> Unit = {
        navigator.back()
    }
}