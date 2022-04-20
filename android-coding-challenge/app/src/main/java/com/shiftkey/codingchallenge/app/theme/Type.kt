package com.shiftkey.codingchallenge.app.theme

import androidx.annotation.DimenRes
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.shiftkey.codingchallenge.R

@Composable
@ReadOnlyComposable
fun spResource(@DimenRes id: Int): TextUnit {
  val context = LocalContext.current
  val density = LocalDensity.current
  val value = context.resources.getDimension(id)
  return (value / density.density).sp
}

@Composable
@ReadOnlyComposable
fun provideTypography(): Typography {
  return Typography(
    h1 = TextStyle(
      fontSize = spResource(id = R.dimen.h1),
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.W300,
      letterSpacing = (-1.5).sp
    ),
    h2 = TextStyle(
      fontSize = spResource(id = R.dimen.h2),
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.W300,
      letterSpacing = (-0.5).sp
    ),
    h3 = TextStyle(
      fontSize = spResource(id = R.dimen.h3),
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.W400
    ),
    h4 = TextStyle(
      fontSize = spResource(id = R.dimen.h4),
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.W400,
      letterSpacing = 0.25.sp
    ),
    h5 = TextStyle(
      fontSize = spResource(id = R.dimen.h5),
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.W400
    ),
    h6 = TextStyle(
      fontSize = spResource(id = R.dimen.h6),
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.W500,
      letterSpacing = 0.15.sp
    ),
    subtitle1 = TextStyle(
      fontSize = spResource(id = R.dimen.subtitle1),
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.W400,
      letterSpacing = 0.15.sp
    ),
    subtitle2 = TextStyle(
      fontSize = spResource(id = R.dimen.subtitle2),
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.W500,
      letterSpacing = 0.1.sp
    ),
    body1 = TextStyle(
      fontSize = spResource(id = R.dimen.body1),
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.W400,
      letterSpacing = 0.5.sp
    ),
    body2 = TextStyle(
      fontSize = spResource(id = R.dimen.body2),
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.W400,
      letterSpacing = 0.25.sp
    ),
    button = TextStyle(
      fontSize = spResource(id = R.dimen.button),
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.W500,
      letterSpacing = 1.25.sp
    ),
    caption = TextStyle(
      fontSize = spResource(id = R.dimen.caption),
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.W400,
      letterSpacing = 0.4.sp
    ),
    overline = TextStyle(
      fontSize = spResource(id = R.dimen.overline),
      fontFamily = FontFamily.Default,
      fontWeight = FontWeight.W400,
      letterSpacing = 1.5.sp
    ),
  )
}