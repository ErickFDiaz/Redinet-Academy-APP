package com.davidchura.sistema1076.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme =
        darkColorScheme(
                primary = PrimaryLight,
                onPrimary = DarkDarkest,
                primaryContainer = PrimaryDark,
                onPrimaryContainer = PrimaryLightest,
                secondary = SecondaryLight,
                onSecondary = DarkDarkest,
                secondaryContainer = SecondaryDark,
                onSecondaryContainer = SecondaryLightest,
                tertiary = Orange,
                background = DarkDarkest,
                onBackground = NeutralWhiteHigh,
                surface = DarkMid,
                onSurface = NeutralWhiteHigh,
                error = StatusError,
                onError = DarkDarkest
        )

private val LightColorScheme =
        lightColorScheme(
                primary = PrimaryMain,
                onPrimary = NeutralWhiteHigh,
                primaryContainer = PrimaryLightest,
                onPrimaryContainer = PrimaryDarkest,
                secondary = SecondaryMain,
                onSecondary = NeutralWhiteHigh,
                secondaryContainer = SecondaryLightest,
                onSecondaryContainer = SecondaryDarkest,
                tertiary = Orange,
                background = NeutralWhiteHigh,
                onBackground = NeutralBlackHigh,
                surface = NeutralWhiteHigh,
                onSurface = NeutralBlackHigh,
                error = StatusError,
                onError = NeutralWhiteHigh
        )

@Composable
fun Sistema1076Theme(
        darkTheme: Boolean = isSystemInDarkTheme(),
        // Dynamic color is available on Android 12+
        dynamicColor: Boolean = false,
        content: @Composable () -> Unit
) {
    val colorScheme =
            when {
                dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                    val context = LocalContext.current
                    if (darkTheme) dynamicDarkColorScheme(context)
                    else dynamicLightColorScheme(context)
                }
                darkTheme -> DarkColorScheme
                else -> LightColorScheme
            }

    MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
