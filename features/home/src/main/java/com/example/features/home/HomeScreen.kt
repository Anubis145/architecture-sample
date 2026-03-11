package com.example.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.ui.effects.snackbar.SnackbarEvent
import com.example.core.ui.effects.snackbar.SnackbarViewModel
import com.example.core.ui.utils.activityViewModel
import com.example.features.home.navigation.HomeExternalNavigator
import com.example.features.home.navigation.HomeNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.navigation.dependency

@Composable
fun DependenciesContainerBuilder<*>.HomeDependencies(
    externalNavigator: HomeExternalNavigator
) {
    dependency(externalNavigator)
}

@Destination<HomeNavGraph>(start = true)
@Composable
fun HomeScreen(
    externalNavigator: HomeExternalNavigator
) {
    HomeScreenContent(
        externalNavigator = externalNavigator
    )
}

@Composable
fun HomeScreenContent(
    viewModel: HomeViewModel = hiltViewModel(),
    externalNavigator: HomeExternalNavigator,
) {
    val snackbarViewModel: SnackbarViewModel = activityViewModel()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

    }
}
