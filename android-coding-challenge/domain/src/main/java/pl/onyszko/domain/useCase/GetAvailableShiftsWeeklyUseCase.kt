package pl.onyszko.domain.useCase

import arrow.core.Either
import pl.onyszko.domain.dataSourceAction.GetAvailableShiftsDataSourceAction
import pl.onyszko.domain.model.ShiftGroup
import java.time.LocalDate
import javax.inject.Inject


data class GetAvailableShiftsWeeklyParam(
  val address: String,
  val start: LocalDate,
  val radius: Int = 2
)

sealed class GetAvailableShiftsWeeklyFailure {
  object Fatal : GetAvailableShiftsWeeklyFailure()
}

class GetAvailableShiftsWeeklyUseCase @Inject constructor(
  private val getAvailableShiftsDataSourceAction: GetAvailableShiftsDataSourceAction,
) :
  ParamUseCase<Either<GetAvailableShiftsWeeklyFailure, List<ShiftGroup>>, GetAvailableShiftsWeeklyParam> {
  private companion object {
    const val type: String = "week"
    const val days: Long = 7
  }

  override suspend fun execute(param: GetAvailableShiftsWeeklyParam): Either<GetAvailableShiftsWeeklyFailure, List<ShiftGroup>> {
    return getAvailableShiftsDataSourceAction.execute(
      type = type,
      start = param.start,
      end = param.start.plusDays(days),
      address = param.address,
      radius = param.radius,
    ).mapLeft { GetAvailableShiftsWeeklyFailure.Fatal }
  }
}