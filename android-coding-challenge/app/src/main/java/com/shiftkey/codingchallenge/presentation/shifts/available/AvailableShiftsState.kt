package com.shiftkey.codingchallenge.presentation.shifts.available

import com.shiftkey.codingchallenge.presentation.common.StateStatus
import pl.onyszko.domain.model.ShiftGroup
import java.time.LocalDate

data class AvailableShiftsState(
  val stateStatus: StateStatus = StateStatus.LOADING,
  val shiftsGroups: List<ShiftGroup> = emptyList(),
  val address: String = "",
  val date: LocalDate = LocalDate.MIN,
  val hasReachedMax: Boolean = false,
)