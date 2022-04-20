package pl.onyszko.remote

import pl.onyszko.domain.model.Shift
import pl.onyszko.domain.model.ShiftGroup
import pl.onyszko.remote.model.*
import java.math.BigDecimal
import java.time.LocalDate
import java.time.OffsetDateTime


val shiftGroup = ShiftGroup(
  lat = BigDecimal.valueOf(32.7766642),
  lng = BigDecimal.valueOf(-96.79698789999999),
  date = LocalDate.of(2022, 4, 22),
  shifts = listOf(
    Shift(
      shiftId = 2769098,
      startTime = OffsetDateTime.parse("2022-04-20T19:00:00+00:00"),
      endTime = OffsetDateTime.parse("2022-04-21T03:00:00+00:00"),
      timezone = "Central",
      premiumRate = true,
      covid = false,
      shiftKind = "Evening Shift",
      withinDistance = 10,
      facilityName = "Skilled Nursing Facility",
      skillName = "Long Term Care",
      localizedSpecialtyName = "Licensed Vocational Nurse",
      localizedSpecialtyAbbreviation = "LVN"
    )
  )
)


val remoteGeoCoordinates = RemoteGeoCoordinates(
  lat = BigDecimal.valueOf(32.7766642),
  lng = BigDecimal.valueOf(-96.79698789999999),
)

val remoteShiftGroup = RemoteShiftGroup(
  date = LocalDate.of(2022, 4, 22),
  shifts = listOf(
    RemoteShift(
      shiftId = 2769098,
      startTime = OffsetDateTime.parse("2022-04-20T19:00:00+00:00"),
      endTime = OffsetDateTime.parse("2022-04-21T03:00:00+00:00"),
      normalizedStartDateTime = "2022-04-20 14:00:00",
      normalizedEndDateTime = "2022-04-20 22:00:00",
      timezone = "Central",
      premiumRate = true,
      covid = false,
      shiftKind = "Evening Shift",
      withinDistance = 10,
      facilityType = RemoteFacilityType(
        id = 8,
        name = "Skilled Nursing Facility",
        color = "#AF52DE",
      ),
      skill = RemoteSkill(
        id = 2,
        name = "Long Term Care",
        color = "#007AFF",
      ),
      localizedSpecialty = RemoteLocalizedSpecialty(
        id = 154,
        specialtyId = 8,
        stateId = 44,
        name = "Licensed Vocational Nurse",
        abbreviation = "LVN",
        specialty = RemoteSpecialty(
          id = 8,
          name = "Licensed Vocational/Practical Nurse",
          color = "#AF52DE",
          abbreviation = "LVN/LPN"
        )
      )
    )
  )
)