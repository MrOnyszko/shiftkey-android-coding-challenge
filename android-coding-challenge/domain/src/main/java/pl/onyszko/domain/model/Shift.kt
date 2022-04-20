package pl.onyszko.domain.model

import java.time.OffsetDateTime

data class Shift(
  val shiftId: Int,
  val startTime: OffsetDateTime,
  val endTime: OffsetDateTime,
  val timezone: String,
  val premiumRate: Boolean,
  val covid: Boolean,
  val shiftKind: String,
  val withinDistance: Int?,
  val facilityName: String,
  val skillName: String,
  val localizedSpecialtyName: String,
  val localizedSpecialtyAbbreviation: String
)