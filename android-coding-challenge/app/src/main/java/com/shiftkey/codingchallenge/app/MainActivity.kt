package com.shiftkey.codingchallenge.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.shiftkey.codingchallenge.app.theme.AppTheme
import com.shiftkey.codingchallenge.presentation.common.DateFormatter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  @Inject
  internal lateinit var dateFormatter: DateFormatter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)

    setContent {
      AppTheme {
        CompositionLocalProvider(
          LocalDateFormatter provides dateFormatter
        ) {
          ProvideWindowInsets {
            Application()
          }
        }
      }
    }
  }
}

@Composable
fun Application() {
  AppNavigation()
}

val LocalDateFormatter = staticCompositionLocalOf<DateFormatter> {
  error("DateFormatter not provided")
}