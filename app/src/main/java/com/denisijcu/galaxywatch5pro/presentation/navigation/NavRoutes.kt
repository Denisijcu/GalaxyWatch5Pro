package com.denisijcu.galaxywatch5pro.presentation.navigation

sealed class NavRoutes(val route:String) {
    object Chat: NavRoutes("chat")
    object ResScreen: NavRoutes("resscreen")
}