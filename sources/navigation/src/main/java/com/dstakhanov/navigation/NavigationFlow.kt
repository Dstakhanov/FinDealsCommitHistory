package com.dstakhanov.navigation

sealed class NavigationFlow {
    object HomeFlow : NavigationFlow()
    class InfoFlow : NavigationFlow()
}