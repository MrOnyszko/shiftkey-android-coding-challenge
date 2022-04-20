package com.shiftkey.codingchallenge.presentation.shifts.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import com.google.accompanist.insets.ui.Scaffold
import com.shiftkey.codingchallenge.R
import com.shiftkey.codingchallenge.presentation.common.DefaultAppBar
import com.shiftkey.codingchallenge.presentation.common.When
import com.shiftkey.codingchallenge.presentation.common.rememberFlowWithLifecycle
import com.shiftkey.codingchallenge.presentation.shifts.available.ShiftProperties

@Composable
fun ShiftDetailsScreen(
  viewModel: ShiftDetailsSharedViewModel,
) {
  val viewState by rememberFlowWithLifecycle(viewModel.stateFlow)
    .collectAsState(initial = ShiftDetailsState())

  ShiftDetailsScreen(viewState)
}

@Composable
private fun ShiftDetailsScreen(
  viewState: ShiftDetailsState
) {
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      DefaultAppBar(titleId = R.string.shift_details_screen_title)
    },
    content = { padding ->
      Column(
        modifier = Modifier
          .fillMaxWidth()
          .padding(paddingValues = padding),
      ) {
        viewState.stateStatus.When(
          loaded = {
            Column(
              modifier = Modifier.padding(
                top = dimensionResource(id = R.dimen.small),
                start = dimensionResource(id = R.dimen.large),
                end = dimensionResource(id = R.dimen.medium),
                bottom = dimensionResource(id = R.dimen.tiny),
              )
            ) {
              val shift = viewState.shift
              if (shift != null) {
                Text(
                  text = viewState.shift.facilityName,
                  style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Bold
                  )
                )
                Spacer(Modifier.height(dimensionResource(id = R.dimen.small)))
                ShiftProperties(shift)
              }
            }
          }
        )
      }
    }
  )
}