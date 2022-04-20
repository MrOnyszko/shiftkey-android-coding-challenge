package pl.onyszko.domain.dataSourceAction

import arrow.core.Either
import pl.onyszko.domain.model.ErrorDetail
import pl.onyszko.domain.model.ShiftGroup
import java.time.LocalDate

interface GetAvailableShiftsDataSourceAction {
  suspend fun execute(
    type: String,
    start: LocalDate,
    end: LocalDate,
    address: String,
    radius: Int,
  ): Either<ErrorDetail, List<ShiftGroup>>
}