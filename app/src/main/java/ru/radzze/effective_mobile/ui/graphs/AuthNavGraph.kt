package ru.radzze.effective_mobile.ui.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.radzze.effective_mobile.ui.auth.AuthScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(route = AuthScreen.Login.route) {
            AuthScreen(onMainNavigate = {
                navController.popBackStack()
                navController.navigate(route = Graph.MAIN)
            })
        }

    }
}

sealed class AuthScreen(val route: String) {
    object Login : AuthScreen(route = "LOGIN")

}