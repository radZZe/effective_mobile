package ru.radzze.effective_mobile.ui.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.radzze.effective_mobile.ui.account.AccountScreen
import ru.radzze.effective_mobile.ui.account.FavoriteScreen
import ru.radzze.effective_mobile.ui.catalog.CatalogScreen
import ru.radzze.effective_mobile.ui.catalog.ProductScreen

fun NavGraphBuilder.accountNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.ACCOUNT,
        startDestination = AccountScreen.Account.route
    ) {
        composable(route = AccountScreen.Account.route) {
            AccountScreen()
        }
        composable(route = AccountScreen.FavoriteScreen.route) {
            FavoriteScreen()
        }

    }
}

sealed class AccountScreen(val route: String) {
    object Account : AccountScreen(route = "ACCOUNT")
    object FavoriteScreen : AccountScreen(route = "FAVORITE")

}