package com.shiftkey.codingchallenge.presentation.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class StateViewModel<Action, State>(
  initialState: State,
  private val dispatcher: CoroutineDispatcher
) : ViewModel() {

  val tag: String = this::class.java.simpleName
  private val pendingActions = MutableSharedFlow<Action>()
  private val _state = MutableStateFlow(initialState)

  val stateFlow: StateFlow<State> = _state

  val state: State
    get() = stateFlow.value

  init {
    viewModelScope.launch(dispatcher) {
      pendingActions.collect { action -> on(action) }
    }
  }

  abstract suspend fun on(action: Action)

  suspend fun emit(state: State) {
    _state.emit(state)
  }

  fun submitAction(action: Action) {
    viewModelScope.launch(dispatcher) {
      pendingActions.emit(action)
    }
  }
}