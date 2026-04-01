package com.example.architecturetest.navigation

import com.example.features.home.navgraphs.HomeGraph
import com.ramcosta.composedestinations.annotation.ExternalNavGraph
import com.ramcosta.composedestinations.annotation.NavHostGraph

@NavHostGraph
annotation class MainGraph {
    @ExternalNavGraph<HomeGraph>(start = true)
    companion object Includes
}