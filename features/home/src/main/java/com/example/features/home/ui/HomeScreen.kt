package com.example.features.home.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.features.home.navigation.HomeExternalNavigator
import com.example.features.home.navigation.HomeNavGraph
import com.example.features.home.ui.mvi.HomeState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DependenciesContainerBuilder
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
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
    viewModel: HomeViewModel = hiltViewModel(),
    externalNavigator: HomeExternalNavigator,
    destinationsNavigator: DestinationsNavigator,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    HomeScreenContent(
        state = state,
        intentListener = viewModel
    )
}

@Composable
fun HomeScreenContent(
    state: HomeState,
    intentListener: HomeIntent,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Home Screen"
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    val intentListener = object : HomeIntent {}

    HomeScreenContent(
        intentListener = intentListener,
        state = HomeState(
            isLoading = false
        )
    )
}
