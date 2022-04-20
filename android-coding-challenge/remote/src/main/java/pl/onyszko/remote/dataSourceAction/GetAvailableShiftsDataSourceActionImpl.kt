package pl.onyszko.remote.dataSourceAction

import arrow.core.Either
import pl.onyszko.domain.dataSourceAction.GetAvailableShiftsDataSourceAction
import pl.onyszko.domain.model.ErrorDetail
import pl.onyszko.domain.model.ShiftGroup
import pl.onyszko.remote.api.ShiftsApi
import pl.onyszko.remote.mapper.RemoteShiftGroupToShiftGroupMapper
import pl.onyszko.remote.model.RemoteErrorDetail
import pl.onyszko.remote.retrofit.ErrorParser
import java.time.LocalDate
import javax.inject.Inject

class GetAvailableShiftsDataSourceActionImpl @Inject constructor(
  private val errorParser: ErrorParser,
  private val shiftsApi: ShiftsApi,
  private val remoteShiftGroupToShiftGroupMapper: RemoteShiftGroupToShiftGroupMapper
) : GetAvailableShiftsDataSourceAction {
  override suspend fun execute(
    type: String,
    start: LocalDate,
    end: LocalDate,
    address: String,
    radius: Int
  ): Either<ErrorDetail, List<ShiftGroup>> {
    return Either.catch(
      { throwable ->
        errorParser.parseErrorBody<RemoteErrorDetail>(throwable)
      },
      {
        shiftsApi.fetchAvailableShifts(
          type,
          start,
          end,
          address,
          radius
        )
      },
    ).bimap(
      leftOperation = { ErrorDetail(code = it.code) },
      rightOperation = { envelope ->
        envelope.data.map { element ->
          remoteShiftGroupToShiftGroupMapper.map(
            element,
            envelope.meta
          )
        }
      }
    )
  }
}