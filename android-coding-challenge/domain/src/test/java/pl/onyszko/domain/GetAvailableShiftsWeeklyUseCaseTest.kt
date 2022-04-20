@file:OptIn(ExperimentalCoroutinesApi::class)

package pl.onyszko.domain

import arrow.core.Either
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import pl.onyszko.domain.dataSourceAction.GetAvailableShiftsDataSourceAction
import pl.onyszko.domain.model.ErrorDetail
import pl.onyszko.domain.useCase.GetAvailableShiftsWeeklyFailure
import pl.onyszko.domain.useCase.GetAvailableShiftsWeeklyParam
import pl.onyszko.domain.useCase.GetAvailableShiftsWeeklyUseCase
import java.time.LocalDate

class GetAvailableShiftsWeeklyUseCaseTest {

  private val mockGetAvailableShiftsDataSourceAction: GetAvailableShiftsDataSourceAction =
    mock()
  private val getAvailableShiftsWeeklyUseCase =
    GetAvailableShiftsWeeklyUseCase(
      getAvailableShiftsDataSourceAction = mockGetAvailableShiftsDataSourceAction
    )

  @Test
  fun `Should get available weekly shifts when no error occurred`() {
    runTest(StandardTestDispatcher()) {

      whenever(
        mockGetAvailableShiftsDataSourceAction.execute(
          type = "week",
          start = LocalDate.of(2022, 4, 20),
          end = LocalDate.of(2022, 4, 27),
          address = "Dallas, TX",
          radius = 2,
        )
      ).thenAnswer {
        Either.Right(listOf(shiftGroup))
      }

      val result = getAvailableShiftsWeeklyUseCase.execute(
        GetAvailableShiftsWeeklyParam(
          address = "Dallas, TX",
          start = LocalDate.of(2022, 4, 20)
        )
      )

      result.fold(
        {
          throw Exception()
        },
        { outcome ->
          assertEquals(outcome, listOf(shiftGroup))
        }
      )
    }
  }

  @Test
  fun `Should return error detail when error occurred`() {
    runTest(StandardTestDispatcher()) {

      whenever(
        mockGetAvailableShiftsDataSourceAction.execute(
          type = "week",
          start = LocalDate.of(2022, 4, 20),
          end = LocalDate.of(2022, 4, 27),
          address = "Dallas, TX",
          radius = 2,
        )
      ).thenAnswer {
        Either.Left(ErrorDetail(code = 0))
      }

      val result = getAvailableShiftsWeeklyUseCase.execute(
        GetAvailableShiftsWeeklyParam(
          address = "Dallas, TX",
          start = LocalDate.of(2022, 4, 20)
        )
      )

      result.fold(
        { outcome ->
          assertEquals(outcome, GetAvailableShiftsWeeklyFailure.Fatal)
        },
        {
          throw Exception()
        }
      )
    }
  }
}
