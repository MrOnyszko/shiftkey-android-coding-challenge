package com.shiftkey.codingchallenge.app.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightThemeColors = lightColors(
  primary = Color700,
  primaryVariant = Color900,
  onPrimary = White80,
  secondary = Color700,
  secondaryVariant = Color900,
  onSecondary = White80,
  error = Error,
  onBackground = Color.Black
)

private val DarkThemeColors = darkColors(
  primary = Color300,
  primaryVariant = Color700,
  onPrimary = Color.Black,
  secondary = Color300,
  onSecondary = Color.Black,
  error = Error,
  onBackground = Color.White
)

@Composable
fun AppTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable () -> Unit
) {
  val colors = if (darkTheme) {
    DarkThemeColors
  } else {
    LightThemeColors
  }

  MaterialTheme(
    colors = colors,
    typography = provideTypography(),
    shapes = Shapes,
    content = content
  )
}