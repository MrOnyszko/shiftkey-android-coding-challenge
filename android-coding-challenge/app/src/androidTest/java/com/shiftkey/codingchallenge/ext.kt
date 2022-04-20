package com.shiftkey.codingchallenge

import androidx.annotation.StringRes
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.shiftkey.codingchallenge.app.MainActivity

fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.getString(
  @StringRes id: Int
): String = activity.getString(id)

fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.onNodeWithString(
  @StringRes id: Int
) = onNodeWithText(getString(id))