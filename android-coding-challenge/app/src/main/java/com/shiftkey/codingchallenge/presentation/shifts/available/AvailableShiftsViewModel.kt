package com.shiftkey.codingchallenge.presentation.shifts.available

import com.shiftkey.codingchallenge.hilt.DefaultCoroutineDispatcher
import com.shiftkey.codingchallenge.presentation.common.StateStatus
import com.shiftkey.codingchallenge.presentation.common.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import pl.onyszko.domain.model.ShiftGroup
import pl.onyszko.domain.useCase.GetAvailableShiftsWeeklyParam
import pl.onyszko.domain.useCase.GetAvailableShiftsWeeklyUseCase
import pl.onyszko.foundation.DateProvider
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AvailableShiftsViewModel @Inject constructor(
  @DefaultCoroutineDispatcher private val dispatcher: CoroutineDispatcher,
  private val getAvailableShiftsWeeklyUseCase: GetAvailableShiftsWeeklyUseCase,
  private val dateProvider: DateProvider,
) : StateViewModel<AvailableShiftsAction, AvailableShiftsState>(
  initialState = AvailableShiftsState(),
  dispatcher = dispatcher
) {

  init {
    submitAction(AvailableShiftsAction.Started(address = "Dallas, TX")) // todo: hardcoded address
  }

  override suspend fun on(action: AvailableShiftsAction) {
    when (action) {
      is AvailableShiftsAction.Started -> {
        getShiftsGroups(
          address = action.address,
          start = dateProvider.offsetDateTimeNow().toLocalDate()
        )
      }
      AvailableShiftsAction.LoadMore -> {
        if (!state.hasReachedMax && state.stateStatus == StateStatus.LOADED) {
          getNextShiftsGroups()
        }
      }
    }
  }

  private suspend fun getShiftsGroups(
    address: String,
    start: LocalDate,
  ) {
    getAvailableShiftsWeeklyUseCase.execute(
      GetAvailableShiftsWeeklyParam(
        address = address,
        start = start
      )
    ).fold(
      { emit(state.copy(stateStatus = StateStatus.ERROR)) },
      { list ->
        if (list.isEmpty()) {
          emit(
            state.copy(
              address = address,
              stateStatus = StateStatus.EMPTY,
              shiftsGroups = list,
              date = LocalDate.MIN,
            )
          )
        } else {
          emit(
            state.copy(
              address = address,
              stateStatus = StateStatus.LOADED,
              shiftsGroups = list,
              date = list.last().date,
            )
          )
        }


      }
    )
  }

  private suspend fun getNextShiftsGroups() {
    getAvailableShiftsWeeklyUseCase.execute(
      GetAvailableShiftsWeeklyParam(
        address = state.address,
        start = state.date
      )
    ).fold(
      { emit(state.copy(stateStatus = StateStatus.ERROR)) },
      { list ->
        val groups = mutableListOf<ShiftGroup>()
        groups.addAll(state.shiftsGroups)
        groups.addAll(list)

        emit(
          state.copy(
            stateStatus = StateStatus.LOADED,
            shiftsGroups = groups,
            hasReachedMax = list.isEmpty(),
            date = list.last().date
          )
        )
      }
    )
  }
}