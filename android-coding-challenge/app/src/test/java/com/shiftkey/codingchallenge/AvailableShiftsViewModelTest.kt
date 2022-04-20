@file:OptIn(ExperimentalCoroutinesApi::class)

package com.shiftkey.codingchallenge

import arrow.core.Either
import com.shiftkey.codingchallenge.presentation.common.StateStatus
import com.shiftkey.codingchallenge.presentation.shifts.available.AvailableShiftsState
import com.shiftkey.codingchallenge.presentation.shifts.available.AvailableShiftsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import pl.onyszko.domain.model.ShiftGroup
import pl.onyszko.domain.useCase.GetAvailableShiftsWeeklyFailure
import pl.onyszko.domain.useCase.GetAvailableShiftsWeeklyParam
import pl.onyszko.domain.useCase.GetAvailableShiftsWeeklyUseCase
import pl.onyszko.foundation.DateProvider
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.ZoneOffset

class AvailableShiftsViewModelTest {

  private val dispatcher = StandardTestDispatcher()

  private val mockGetAvailableShiftsWeeklyUseCase: GetAvailableShiftsWeeklyUseCase =
    mock()
  private val mockDateProvider: DateProvider = mock {
    on { offsetDateTimeNow() }.thenReturn(
      OffsetDateTime.of(2022, 4, 20, 0, 0, 0, 0, ZoneOffset.UTC)
    )
  }

  private val availableShiftsViewModel = AvailableShiftsViewModel(
    dispatcher = dispatcher,
    getAvailableShiftsWeeklyUseCase = mockGetAvailableShiftsWeeklyUseCase,
    dateProvider = mockDateProvider
  )

  @Test
  fun `On action started should get first page of shifts groups when no errors occurred`() {
    runTest(dispatcher) {
      val shiftGroups = listOf(shiftGroup)

      whenever(
        mockGetAvailableShiftsWeeklyUseCase.execute(
          GetAvailableShiftsWeeklyParam(
            address = "Dallas, TX",
            start = LocalDate.of(2022, 4, 20),
          )
        )
      ).thenAnswer {
        Either.Right(shiftGroups)
      }

      launch {
        val currentStates =
          availableShiftsViewModel.stateFlow.take(2).toList()

        val state = AvailableShiftsState(
          stateStatus = StateStatus.LOADED,
          shiftsGroups = shiftGroups,
          address = "Dallas, TX",
          date = LocalDate.of(2022, 4, 20),
          hasReachedMax = false
        )

        val states = listOf(
          AvailableShiftsState(),
          state
        )

        assertEquals(currentStates, states)
      }
    }
  }

  @Test
  fun `On action started should get empty page of shifts groups when there is no shifts available`() {
    runTest(dispatcher) {
      whenever(
        mockGetAvailableShiftsWeeklyUseCase.execute(
          GetAvailableShiftsWeeklyParam(
            address = "Dallas, TX",
            start = LocalDate.of(2022, 4, 20),
          )
        )
      ).thenAnswer {
        Either.Right(listOf<ShiftGroup>())
      }

      launch {
        val currentStates =
          availableShiftsViewModel.stateFlow.take(2).toList()

        val state = AvailableShiftsState(
          stateStatus = StateStatus.EMPTY,
          shiftsGroups = listOf(),
          address = "Dallas, TX",
          date = LocalDate.MIN,
          hasReachedMax = false
        )

        val states = listOf(
          AvailableShiftsState(),
          state
        )

        assertEquals(currentStates, states)
      }
    }
  }

  @Test
  fun `On action started should return error state when there error occurred`() {
    runTest(dispatcher) {
      whenever(
        mockGetAvailableShiftsWeeklyUseCase.execute(
          GetAvailableShiftsWeeklyParam(
            address = "Dallas, TX",
            start = LocalDate.of(2022, 4, 20),
          )
        )
      ).thenAnswer {
        Either.Left(GetAvailableShiftsWeeklyFailure.Fatal)
      }

      launch {
        val currentStates =
          availableShiftsViewModel.stateFlow.take(2).toList()

        val state = AvailableShiftsState(
          stateStatus = StateStatus.ERROR,
        )

        val states = listOf(
          AvailableShiftsState(),
          state
        )

        assertEquals(currentStates, states)
      }
    }
  }
}