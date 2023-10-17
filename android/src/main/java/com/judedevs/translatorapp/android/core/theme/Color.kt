package com.judedevs.translatorapp.android.core.theme

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color
import com.judedevs.translatorapp.core.presentation.Colors

val DarkBlueGrey = Color(Colors.DarkBlueGrey)
val LightBlueGrey = Color(Colors.LightBlueGrey)
val TextBlack = Color(Colors.TextBlack)
val LightGrey = Color(Colors.LightGrey)

val lightThemeColors = lightColors(
    primary = DarkBlueGrey,
    background = LightBlueGrey,
    onPrimary = Color.White,
    onBackground = TextBlack,
    surface = Color.White,
    onSurface = TextBlack
)

val darkThemeColors = darkColors(
    primary = LightGrey,
    background = DarkBlueGrey,
    onPrimary = DarkBlueGrey,
    onBackground = Color.White,
    surface = DarkBlueGrey,
    onSurface = Color.White
)