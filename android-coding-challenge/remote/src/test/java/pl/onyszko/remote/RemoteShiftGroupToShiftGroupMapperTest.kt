package pl.onyszko.remote

import org.junit.Assert.assertEquals
import org.junit.Test
import pl.onyszko.remote.mapper.RemoteShiftGroupToShiftGroupMapper

class RemoteShiftGroupToShiftGroupMapperTest {
  @Test
  fun `Should map RemoteShiftGroup to ShiftGroup when correct data provided`() {
    val sut = RemoteShiftGroupToShiftGroupMapper()

    val result = sut.map(
      value = remoteShiftGroup,
      arg = remoteGeoCoordinates
    )

    assertEquals(result, shiftGroup)
  }
}
