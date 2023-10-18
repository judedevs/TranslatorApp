package com.judedevs.translatorapp.android.core.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import com.judedevs.translatorapp.core.presentation.Colors

val LightBlueGrey = Color(Colors.LightBlueGrey)
val DarkBlueGrey = Color(Colors.DarkBlueGrey)
val TextBlack = Color(Colors.TextBlack)
val LightGrey = Color(Colors.LightGrey)
val DarkGrey = Color(Colors.DarkGrey)

val lightThemeColors = lightColors(
    primary = DarkBlueGrey,
    background = LightBlueGrey,
    onPrimary = Color.White,
    onBackground = DarkBlueGrey,
    surface = Color.White,
    onSurface = TextBlack
)

val darkThemeColors = darkColors(
    primary = LightGrey,
    background = DarkBlueGrey,
    onPrimary = DarkBlueGrey,
    onBackground = Color.White,
    surface = LightBlueGrey,
    onSurface = TextBlack
)