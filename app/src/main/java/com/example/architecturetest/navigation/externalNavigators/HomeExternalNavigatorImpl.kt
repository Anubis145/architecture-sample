package com.example.architecturetest.navigation.externalNavigators

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.features.home.navigation.HomeExternalNavigator
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.utils.rememberDestinationsNavigator

@Composable
fun createHomeExternalNavigator(
    navController: NavController,
) : HomeExternalNavigator {
    val destinationsNavigator = navController.rememberDestinationsNavigator()
    return HomeExternalNavigatorImpl(destinationsNavigator)
}

class HomeExternalNavigatorImpl(
    private val destinationsNavigator: DestinationsNavigator,
) : HomeExternalNavigator {
    override fun onNavigateBack() {
        destinationsNavigator.navigateUp()
    }
}