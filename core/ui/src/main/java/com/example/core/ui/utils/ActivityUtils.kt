package com.example.core.ui.utils

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
inline fun <reified VM : ViewModel> activityViewModel(): VM {
    val context = LocalContext.current
    val activity = context as ComponentActivity
    return viewModel(activity)
}
