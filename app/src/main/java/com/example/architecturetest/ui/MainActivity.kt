package com.example.architecturetest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.architecturetest.navigation.externalNavigators.createHomeExternalNavigator
import com.example.core.ui.theme.ArchTheme
import com.example.features.home.HomeDependencies
import com.example.features.home.navgraphs.HomeGraph
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs
import com.ramcosta.composedestinations.navigation.navGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ArchTheme {
                val navController = rememberNavController()

                val homeGraphExternalNavigator = createHomeExternalNavigator(navController)

                Scaffold(

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
