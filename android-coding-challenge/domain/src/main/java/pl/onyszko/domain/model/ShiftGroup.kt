package pl.onyszko.domain.model

import java.math.BigDecimal
import java.time.LocalDate

data class ShiftGroup(
  val lat: BigDecimal?,
  val lng: BigDecimal?,
  val date: LocalDate,
  val shifts: List<Shift>
)