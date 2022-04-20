package com.shiftkey.codingchallenge

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.accompanist.insets.ProvideWindowInsets
import com.shiftkey.codingchallenge.app.LocalDateFormatter
import com.shiftkey.codingchallenge.app.MainActivity
import com.shiftkey.codingchallenge.app.theme.AppTheme
import com.shiftkey.codingchallenge.presentation.common.DateFormatter
import com.shiftkey.codingchallenge.presentation.common.LoadingTestTag
import com.shiftkey.codingchallenge.presentation.common.StateStatus
import com.shiftkey.codingchallenge.presentation.shifts.available.AvailableShiftsScreen
import com.shiftkey.codingchallenge.presentation.shifts.available.AvailableShiftsState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@RunWith(AndroidJUnit4::class)
class AvailableShiftsScreenTest {

  @get:Rule
  val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity> =
    createAndroidComposeRule<MainActivity>()

  private val dateFormatter: DateFormatter = DateFormatter(
    shortDateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT),
    formatDayName = DateTimeFormatter.ofPattern("EEEE")
  )

  @Test
  fun on_initial_state_should_display_loading_indicator() {
    composeTestRule.setContent {
      AppTheme {
        AvailableShiftsScreen(
          viewState = AvailableShiftsState(),
          onShiftTapped = {},
          loadMore = {}
        )
      }
    }

    composeTestRule.onNodeWithTag(LoadingTestTag).assertExists()
  }

  @Test
  fun on_loaded_state_should_display_list_of_shifts_groups() {
    composeTestRule.setContent {
      AppTheme {
        CompositionLocalProvider(
          LocalDateFormatter provides dateFormatter
        ) {
          ProvideWindowInsets {
            AvailableShiftsScreen(
              viewState = AvailableShiftsState(
                stateStatus = StateStatus.LOADED,
                shiftsGroups = listOf(lvnShiftGroup),
                address = "Dallas, TX",
                date = LocalDate.of(2022, 4, 20),
                hasReachedMax = false
              ),
              onShiftTapped = {},
              loadMore = {}
            )
          }
        }
      }
    }

    composeTestRule.onNodeWithTag(LoadingTestTag).assertDoesNotExist()
    composeTestRule.onNodeWithText("Skilled Nursing Facility")
      .assertIsDisplayed()
    composeTestRule.onNodeWithText("Within 10 ml").assertIsDisplayed()
    composeTestRule.onNodeWithText("Evening Shift").assertIsDisplayed()
    composeTestRule.onNodeWithText("Long Term Care").assertIsDisplayed()
    composeTestRule.onNodeWithText("LVN").assertIsDisplayed()
  }
}