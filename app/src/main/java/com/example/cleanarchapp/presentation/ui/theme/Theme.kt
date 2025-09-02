package com.example.cleanarchapp.presentation.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = bluePrimaryDark,
    secondary = blueSecondaryDark,
    tertiary = tealTertiaryDark,
    background = backgroundDark,
    surface = surfaceDark,
    onBackground = onBackgroundDark,
    onPrimary = onBluePrimaryDark,
    onSecondary = onBlueSecondaryDark,
    onTertiary = onTealTertiaryDark,
    onSurface = onSurfaceDark
)

private val LightColorScheme = lightColorScheme(
    primary = bluePrimary,
    secondary = blueSecondary,
    tertiary = tealTertiary,
    background = backgroundLight,
    surface = surfaceLight,
    onBackground = onBackgroundLight,
    onPrimary = onBluePrimary,
    onSecondary = onBlueSecondary,
    onTertiary = onTealTertiary,
    onSurface = onSurfaceLight
)

@Composable
fun CleanArchAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}