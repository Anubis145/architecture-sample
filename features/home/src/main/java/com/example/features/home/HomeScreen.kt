package com.example.features.home

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
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

}
