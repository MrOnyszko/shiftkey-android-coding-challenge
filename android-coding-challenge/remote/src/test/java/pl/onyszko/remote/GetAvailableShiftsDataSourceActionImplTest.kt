@file:OptIn(ExperimentalCoroutinesApi::class)

package pl.onyszko.remote

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import pl.onyszko.domain.model.ErrorDetail
import pl.onyszko.remote.api.ShiftsApi
import pl.onyszko.remote.dataSourceAction.GetAvailableShiftsDataSourceActionImpl
import pl.onyszko.remote.mapper.RemoteShiftGroupToShiftGroupMapper
import pl.onyszko.remote.model.Envelope
import pl.onyszko.remote.retrofit.ErrorParser
import java.time.LocalDate

class GetAvailableShiftsDataSourceActionImplTest {

  private val mockErrorParser: ErrorParser = mock()
  private val mockShiftsApi: ShiftsApi = mock()
  private val mockRemoteShiftGroupToShiftGroupMapper: RemoteShiftGroupToShiftGroupMapper =
    mock()

  private val getAvailableShiftsDataSourceAction =
    GetAvailableShiftsDataSourceActionImpl(
      errorParser = mockErrorParser,
      shiftsApi = mockShiftsApi,
      remoteShiftGroupToShiftGroupMapper = mockRemoteShiftGroupToShiftGroupMapper,
    )

  @Test
  fun `Should fetch available shifts when no error occurred`() {
    runTest(StandardTestDispatcher()) {

      whenever(
        mockShiftsApi.fetchAvailableShifts(
          type = "week",
          start = LocalDate.of(2022, 4, 20),
          end = LocalDate.of(2022, 4, 27),
          address = "Dallas, TX",
          radius = 5,
        )
      ).thenAnswer {
        Envelope(listOf(remoteShiftGroup), remoteGeoCoordinates)
      }

      whenever(
        mockRemoteShiftGroupToShiftGroupMapper.map(
          remoteShiftGroup,
          remoteGeoCoordinates
        )
      ).thenReturn(shiftGroup)

      val result = getAvailableShiftsDataSourceAction.execute(
        type = "week",
        start = LocalDate.of(2022, 4, 20),
        end = LocalDate.of(2022, 4, 27),
        address = "Dallas, TX",
        radius = 5,
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
        mockShiftsApi.fetchAvailableShifts(
          type = "week",
          start = LocalDate.of(2022, 4, 20),
          end = LocalDate.of(2022, 4, 27),
          address = "Dallas, TX",
          radius = 5,
        )
      ).thenThrow(RuntimeException::class.java)

      val result = getAvailableShiftsDataSourceAction.execute(
        type = "week",
        start = LocalDate.of(2022, 4, 20),
        end = LocalDate.of(2022, 4, 27),
        address = "Dallas, TX",
        radius = 5,
      )

      result.fold(
        { outcome ->
          assertEquals(outcome, ErrorDetail(code = 0))
        },
        {
          throw Exception()
        },
      )
    }
  }
}
