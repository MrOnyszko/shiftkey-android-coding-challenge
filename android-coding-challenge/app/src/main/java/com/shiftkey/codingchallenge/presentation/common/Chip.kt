package com.shiftkey.codingchallenge.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.shiftkey.codingchallenge.R

@Preview(showBackground = true)
@Composable
fun Chip(
  name: String = "Chip",
  elevation: Dp = 1.dp,
  color: Color = MaterialTheme.colors.primary,
) {
  Surface(
    modifier = Modifier.padding(dimensionResource(id = R.dimen.tiny)),
    elevation = elevation,
    shape = RoundedCornerShape(dimensionResource(id = R.dimen.large)),
    color = color
  ) {
    Row {
      Text(
        text = name,
        style = MaterialTheme.typography.caption,
        color = Color.White,
        modifier = Modifier.padding(dimensionResource(id = R.dimen.small))
      )
    }
  }
}