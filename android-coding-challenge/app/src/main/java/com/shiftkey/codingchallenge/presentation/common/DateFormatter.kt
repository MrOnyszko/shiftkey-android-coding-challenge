package com.shiftkey.codingchallenge.presentation.common

import com.shiftkey.codingchallenge.hilt.FormatDayName
import com.shiftkey.codingchallenge.hilt.ShortDate
import java.time.format.DateTimeFormatter
import java.time.temporal.Temporal
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DateFormatter @Inject constructor(
  @ShortDate private val shortDateFormatter: DateTimeFormatter,
  @FormatDayName private val formatDayName: DateTimeFormatter
) {

  fun formatShortDate(temporalAmount: Temporal): String =
    shortDateFormatter.format(temporalAmount)

  fun formatDayName(temporalAmount: Temporal): String =
    formatDayName.format(temporalAmount)
}