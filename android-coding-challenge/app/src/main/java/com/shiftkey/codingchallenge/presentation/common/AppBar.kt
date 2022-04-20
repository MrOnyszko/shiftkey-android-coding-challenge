package com.shiftkey.codingchallenge.presentation.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import com.shiftkey.codingchallenge.R

@Composable
fun DefaultAppBar(
  modifier: Modifier = Modifier,
  @StringRes titleId: Int = R.string.app_name,
  elevation: Dp = AppBarDefaults.TopAppBarElevation,
  navigationIcon: @Composable (() -> Unit)? = null,
  actions: @Composable RowScope.() -> Unit = {},
) {
  TopAppBar(
    title = {
      Text(stringResource(id = titleId))
    },
    elevation = elevation,
    navigationIcon = navigationIcon,
    actions = actions,
    modifier = modifier,
    contentPadding = rememberInsetsPaddingValues(
      insets = LocalWindowInsets.current.systemBars,
      applyBottom = false,
    )
  )
}

@Composable
fun NavigationBackIcon(
  onClick: () -> Unit,
) {
  IconButton(onClick = onClick) {
    Icon(
      imageVector = Icons.Filled.ArrowBack,
      contentDescription = stringResource(id = R.string.navigation_icon_action_content_description)
    )
  }
}