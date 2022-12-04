package com.example.firstapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.example.fisrtapp.ui.theme.Green200
import com.example.fisrtapp.ui.theme.Teal200
import com.example.fisrtapp.ui.theme.Teal500
import com.example.fisrtapp.ui.theme.Teal700

private val DarkColorPalette = darkColors(
        primary = Teal200,
        primaryVariant = Teal700,
        secondary = Green200
)

private val LightColorPalette = lightColors(
        primary = Teal500,
        primaryVariant = Teal700,
        secondary = Green200

        /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun FirstAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
    )
}