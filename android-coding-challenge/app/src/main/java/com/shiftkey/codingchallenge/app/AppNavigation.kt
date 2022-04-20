package com.shiftkey.codingchallenge.app

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shiftkey.codingchallenge.presentation.shifts.available.AvailableShiftsRoute
import com.shiftkey.codingchallenge.presentation.shifts.available.AvailableShiftsScreen
import com.shiftkey.codingchallenge.presentation.shifts.details.ShiftDetailsRoute
import com.shiftkey.codingchallenge.presentation.shifts.details.ShiftDetailsScreen

sealed class ScreenRoute {
  abstract val routeName: String

  abstract class Route : ScreenRoute()
}

@Composable
fun AppNavigation() {
  val navController = rememberNavController()
  NavHost(
    navController = navController,
    startDestination = AvailableShiftsRoute.routeName,
  ) {
    composable(route = AvailableShiftsRoute.routeName) {
      AvailableShiftsScreen(
        viewModel = hiltViewModel(),
        onShiftTapped = { shift ->
          navController.navigate(ShiftDetailsRoute.createRoute(shift.shiftId))
        }
      )
    }
    composable(
      route = ShiftDetailsRoute.routeName,
      arguments = ShiftDetailsRoute.arguments(),
    ) {
      ShiftDetailsScreen(
        viewModel = hiltViewModel(),
      )
    }
  }
}