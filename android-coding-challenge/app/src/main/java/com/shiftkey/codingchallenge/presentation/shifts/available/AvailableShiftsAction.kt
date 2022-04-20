package com.shiftkey.codingchallenge.presentation.shifts.available

sealed class AvailableShiftsAction {
  data class Started(
    val address: String,
  ) : AvailableShiftsAction()

  object LoadMore : AvailableShiftsAction()
}