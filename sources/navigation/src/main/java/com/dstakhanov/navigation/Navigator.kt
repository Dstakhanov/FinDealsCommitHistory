package com.dstakhanov.navigation

import androidx.navigation.NavController

class Navigator {
    lateinit var navController: NavController

    // navigate on main thread or nav component crashes sometimes
    fun navigateToFlow(navigationFlow: NavigationFlow) {
    }
}