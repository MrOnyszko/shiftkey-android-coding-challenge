package com.shiftkey.codingchallenge.presentation.common

import androidx.compose.runtime.Composable

enum class StateStatus {
  LOADING,
  LOADED,
  EMPTY,
  ERROR
}

@Composable
fun StateStatus.When(
  loading: @Composable (() -> Unit) = {
    Loading()
  },
  empty: @Composable (() -> Unit) = {
    Empty()
  },
  error: @Composable (() -> Unit) = {
    Error()
  },
  loaded: @Composable (() -> Unit),
) {
  when (this) {
    StateStatus.LOADING -> loading()
    StateStatus.LOADED -> loaded()
    StateStatus.EMPTY -> empty()
    StateStatus.ERROR -> error()
  }
}