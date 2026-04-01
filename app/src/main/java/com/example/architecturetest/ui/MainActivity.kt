package com.example.architecturetest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.compose.rememberNavController
import com.example.architecturetest.navigation.externalNavigators.createHomeExternalNavigator
import com.example.core.ui.components.snackbar.ArchSnackbar
import com.example.core.ui.components.snackbar.ArchSnackbarVisuals
import com.example.core.ui.effects.snackbar.SnackbarEvent
import com.example.core.ui.effects.snackbar.SnackbarViewModel
import com.example.core.ui.utils.ObserveAsEvents
import com.example.core.ui.theme.ArchTheme
import com.example.features.home.ui.HomeDependencies
import com.example.features.home.navgraphs.HomeGraph
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.navigation.navGraph
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val snackbarViewModel by viewModels<SnackbarViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ArchTheme {
                val navController = rememberNavController()
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()
                val homeGraphExternalNavigator = createHomeExternalNavigator(navController)

                ObserveAsEvents(
                    flow = snackbarViewModel.snackbarFlow,
                    lifecycleState = Lifecycle.State.STARTED,
                    key1 = snackbarHostState,
                    onEvent = { snackbarEvent ->
                        scope.launch {
                            snackbarHostState.currentSnackbarData?.dismiss()

                            val snackbar = when (snackbarEvent.type) {
                                SnackbarEvent.SnackbarType.WARNING -> {
                                    ArchSnackbarVisuals.Warning(message = snackbarEvent.message)
                                }
                                SnackbarEvent.SnackbarType.ERROR -> {
                                    ArchSnackbarVisuals.Error(message = snackbarEvent.message)
                                }
                            }

                            snackbarHostState.showSnackbar(snackbar)
                        }
                    }
                )

                Scaffold(
                    modifier = Modifier,
                    snackbarHost = {
                        SnackbarHost(
                            hostState = snackbarHostState,
                            modifier = Modifier.fillMaxWidth(),
                            snackbar = { ArchSnackbar(it) }
                        )
                    }
                ) { innerPaddings ->
                    DestinationsNavHost(
                        navGraph = NavGraphs.main,
                        navController = navController,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPaddings),
                        dependenciesContainerBuilder = {
                            navGraph(HomeGraph) {
                                val homeExternalNavigator = remember(navBackStackEntry) {
                                    homeGraphExternalNavigator
                                }
                                HomeDependencies(homeExternalNavigator)
                            }
                        }
                    )
                }
            }
        }
    }
}
