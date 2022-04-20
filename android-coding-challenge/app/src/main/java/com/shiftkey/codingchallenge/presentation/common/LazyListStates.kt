package com.shiftkey.codingchallenge.presentation.common

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.MutableState

fun LazyListState.shouldLoadMore(rememberedIndex: MutableState<Int>): Boolean {
  val firstVisibleIndex = this.firstVisibleItemIndex
  if (rememberedIndex.value != firstVisibleIndex) {
    rememberedIndex.value = firstVisibleIndex
    return layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
  }
  return false
}