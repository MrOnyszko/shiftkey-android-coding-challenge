package pl.onyszko.domain

import pl.onyszko.domain.model.Shift
import pl.onyszko.domain.model.ShiftGroup
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