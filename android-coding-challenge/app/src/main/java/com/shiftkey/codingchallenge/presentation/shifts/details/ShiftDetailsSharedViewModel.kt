package com.shiftkey.codingchallenge.presentation.shifts.details

import com.shiftkey.codingchallenge.hilt.DefaultCoroutineDispatcher
import com.shiftkey.codingchallenge.presentation.common.StateStatus
import com.shiftkey.codingchallenge.presentation.common.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class ShiftDetailsSharedViewModel @Inject constructor(
  @DefaultCoroutineDispatcher private val dispatcher: CoroutineDispatcher,
  private val shiftDetailsArguments: ShiftDetailsArguments,
) : StateViewModel<ShiftDetailsAction, ShiftDetailsState>(
  initialState = ShiftDetailsState(),
  dispatcher = dispatcher
) {
  // todo: it would be better to use ShiftDetailsArguments.shiftId to fetch
  //  fresh details from API

  override suspend fun on(action: ShiftDetailsAction) {
    when (action) {
      is ShiftDetailsAction.ShiftSelected -> {
        emit(
          state.copy(
            stateStatus = StateStatus.LOADED,
            shift = action.shift
          )
        )
      }
    }
  }
}