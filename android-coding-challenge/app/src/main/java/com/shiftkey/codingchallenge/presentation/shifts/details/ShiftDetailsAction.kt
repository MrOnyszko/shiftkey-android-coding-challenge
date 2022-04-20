package com.shiftkey.codingchallenge.presentation.shifts.details

import pl.onyszko.domain.model.Shift

sealed class ShiftDetailsAction {
  data class ShiftSelected(
    val shift: Shift,
  ) : ShiftDetailsAction()
}