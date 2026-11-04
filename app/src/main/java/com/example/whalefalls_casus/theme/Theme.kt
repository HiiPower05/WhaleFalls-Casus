package com.example.whalefalls_casus.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = DeepGreen,
    onPrimary = Color.White,
    primaryContainer = SageGreen,
    onPrimaryContainer = Color.White,
    secondary = SageGreen,
    onSecondary = Color.White,
    background = CreamBackground,
    onBackground = TextPrimary,
    surface = CardSurface,
    onSurface = TextPrimary,
    surfaceVariant = Color(0xFFF0EDE0),
    onSurfaceVariant = TextSecondary,
    outline = SurfaceBorder
)

private val DarkColorScheme = darkColorScheme(
    primary = SageGreen,
    onPrimary = Color.White,
    primaryContainer = DeepGreen,
    onPrimaryContainer = Color.White,
    secondary = SageGreen,
    onSecondary = Color.White,
    background = Color(0xFF121A16),
    onBackground = Color(0xFFE8EDE8),
    surface = Color(0xFF1D2922),
    onSurface = Color(0xFFE8EDE8),
    surfaceVariant = Color(0xFF303D35),
    onSurfaceVariant = Color(0xFFC0CCC2),
    outline = Color(0xFF59665D)
)

@Composable
fun WhaleFallsCasusTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}