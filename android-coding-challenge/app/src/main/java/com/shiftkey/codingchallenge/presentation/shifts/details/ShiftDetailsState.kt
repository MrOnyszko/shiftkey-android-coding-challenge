package com.shiftkey.codingchallenge.presentation.shifts.details

import com.shiftkey.codingchallenge.presentation.common.StateStatus
import pl.onyszko.domain.model.Shift

data class ShiftDetailsState(
  val stateStatus: StateStatus = StateStatus.LOADING,
  val shift: Shift? = null,
)