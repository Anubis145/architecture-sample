package com.example.features.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.ui.components.inputField.SearchInputField
import com.example.core.ui.components.spacer.Spacer
import com.example.features.home.R
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
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Spacer(16.dp)
        SearchInputField(
            modifier = Modifier.padding(horizontal = 16.dp),
            value = state.searchValue,
            onValueChange = {
                intentListener.onSearchValueChanged(it)
            },
            hint = stringResource(R.string.search)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    val intentListener = object : HomeIntent {
        override fun onSearchValueChanged(value: String) {}
    }

    HomeScreenContent(
        intentListener = intentListener,
        state = HomeState(
            isLoading = false
        )
    )
}
