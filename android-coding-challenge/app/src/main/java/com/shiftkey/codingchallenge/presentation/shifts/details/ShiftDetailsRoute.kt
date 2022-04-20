@file:Suppress("MemberVisibilityCanBePrivate")

package com.shiftkey.codingchallenge.presentation.shifts.details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.shiftkey.codingchallenge.app.ScreenRoute
import javax.inject.Inject

object ShiftDetailsRoute : ScreenRoute.Route() {
  const val name = "shift-details"

  const val shiftIdArgName = "shiftId"

  override val routeName: String
    get() = "$name/{$shiftIdArgName}"

  fun createRoute(shiftId: Int): String = "$name/$shiftId"

  fun arguments(): List<NamedNavArgument> {
    return listOf(
      navArgument(name = shiftIdArgName) {
        type = NavType.IntType
      },
    )
  }
}

class ShiftDetailsArguments @Inject constructor(
  private val savedStateHandle: SavedStateHandle
) {
  val shiftId: Int? by lazy {
    savedStateHandle[ShiftDetailsRoute.shiftIdArgName]
  }
}