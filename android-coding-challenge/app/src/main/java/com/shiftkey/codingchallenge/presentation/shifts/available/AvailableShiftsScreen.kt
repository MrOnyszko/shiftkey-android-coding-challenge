@file:OptIn(ExperimentalFoundationApi::class)

package com.shiftkey.codingchallenge.presentation.shifts.available

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.ui.Scaffold
import com.shiftkey.codingchallenge.R
import com.shiftkey.codingchallenge.app.LocalDateFormatter
import com.shiftkey.codingchallenge.presentation.common.DefaultAppBar
import com.shiftkey.codingchallenge.presentation.common.InfiniteListHandler
import com.shiftkey.codingchallenge.presentation.common.When
import com.shiftkey.codingchallenge.presentation.common.rememberFlowWithLifecycle
import pl.onyszko.domain.model.Shift
import pl.onyszko.domain.model.ShiftGroup

@Composable
fun AvailableShiftsScreen(
  viewModel: AvailableShiftsViewModel,
  onShiftTapped: (shift: Shift) -> Unit,
) {
  val viewState by rememberFlowWithLifecycle(viewModel.stateFlow)
    .collectAsState(initial = AvailableShiftsState())

  AvailableShiftsScreen(
    viewState = viewState,
    onShiftTapped = onShiftTapped,
    loadMore = {
      viewModel.submitAction(AvailableShiftsAction.LoadMore)
    },
  )
}

@Composable
fun AvailableShiftsScreen(
  viewState: AvailableShiftsState,
  onShiftTapped: (shift: Shift) -> Unit = {},
  loadMore: () -> Unit = {},
) {
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      DefaultAppBar(titleId = R.string.available_shifts_screen_title)
    },
    content = { padding ->
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(paddingValues = padding),
      ) {
        viewState.stateStatus.When(
          loaded = {
            ShiftsStickyHeadersList(
              entries = viewState.shiftsGroups,
              onItemTap = {
                onShiftTapped(it)
              },
              loadMore = loadMore,
            )
          }
        )
      }
    }
  )
}

@ExperimentalFoundationApi
@Composable
fun ShiftsStickyHeadersList(
  entries: List<ShiftGroup>,
  onItemTap: (item: Shift) -> Unit,
  loadMore: () -> Unit = {},
) {
  val listState = rememberLazyListState()

  LazyColumn(state = listState) {
    entries.forEach { group ->
      stickyHeader {
        DayHeader(group)
      }
      items(group.shifts) { shift ->
        ShiftItem(
          modifier = Modifier
            .fillMaxSize()
            .clickable {
              onItemTap(shift)
            },
          shift = shift,
        )
      }
    }
    item {
      Spacer(
        modifier = Modifier.height(dimensionResource(id = R.dimen.xBig))
      )
    }
  }

  InfiniteListHandler(listState = listState) {
    loadMore()
  }
}

@Composable
private fun DayHeader(day: ShiftGroup) {
  val localFormatter = LocalDateFormatter.current

  Surface(
    elevation = 0.5.dp
  ) {
    Row(
      Modifier
        .background(MaterialTheme.colors.background)
        .padding(all = dimensionResource(id = R.dimen.medium))
        .fillMaxSize(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        localFormatter.formatDayName(day.date),
        textAlign = TextAlign.Start,
        style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
      )
      Spacer(Modifier.width(dimensionResource(id = R.dimen.large)))
      Text(
        localFormatter.formatShortDate(day.date),
        textAlign = TextAlign.Start,
      )
    }
  }
}


@Composable
private fun ShiftItem(
  modifier: Modifier = Modifier,
  shift: Shift
) {
  Column(
    modifier = modifier.padding(
      top = dimensionResource(id = R.dimen.small),
      start = dimensionResource(id = R.dimen.large),
      end = dimensionResource(id = R.dimen.medium),
      bottom = dimensionResource(id = R.dimen.tiny),
    )
  ) {
    Text(
      text = shift.facilityName,
      style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold)
    )
    Spacer(Modifier.height(dimensionResource(id = R.dimen.small)))
    ShiftProperties(shift)
  }
}

@Composable
fun ShiftProperties(shift: Shift) {
  Column {
    if (shift.withinDistance != null)
      Text(
        modifier = Modifier.padding(end = 2.dp),
        text = stringResource(
          id = R.string.available_shifts_screen_shift_distance_label,
          shift.withinDistance ?: 0
        ),
      )
    if (shift.premiumRate)
      Text(
        modifier = Modifier.padding(end = 2.dp),
        text = stringResource(id = R.string.available_shifts_screen_shift_premium_rate_label),
      )
    if (shift.covid)
      Text(
        modifier = Modifier.padding(end = 2.dp),
        text = stringResource(id = R.string.available_shifts_screen_shift_covid_label),
      )
    Text(
      modifier = Modifier.padding(end = 2.dp),
      text = shift.shiftKind,
    )
    Text(
      modifier = Modifier.padding(end = 2.dp),
      text = shift.skillName,
    )
    Text(
      modifier = Modifier.padding(end = 2.dp),
      text = shift.localizedSpecialtyAbbreviation,
    )
  }
}