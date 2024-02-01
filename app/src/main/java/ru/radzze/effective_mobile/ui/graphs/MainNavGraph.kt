package ru.radzze.effective_mobile.ui.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.radzze.effective_mobile.ui.BottomNavScreen
import ru.radzze.effective_mobile.ui.HomeScreen
import ru.radzze.effective_mobile.ui.account.AccountScreen
import ru.radzze.effective_mobile.ui.catalog.CatalogScreen
import ru.radzze.effective_mobile.ui.main.MainScreen


@Composable
fun MainNavGraph(navController: NavHostController,isAuth:Boolean) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = if(isAuth)BottomNavScreen.Catalog.route else BottomNavScreen.Main.route
    ) {
        composable(route = BottomNavScreen.Main.route) {
            MainScreen()
        }
        composable(route = BottomNavScreen.Catalog.route) {
            CatalogScreen()
        }
        composable(route = BottomNavScreen.Cart.route) {

        }
        composable(route = BottomNavScreen.Stock.route) {

        }
        composable(route = BottomNavScreen.Account.route) {
            AccountScreen()
        }
    }
}

fun NavGraphBuilder.mainNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.MAIN,
        startDestination = MainScreen.Main.route
    ) {
        composable(route = MainScreen.Main.route) {
            MainScreen()
        }

    }
}

sealed class MainScreen(val route: String) {
    object Main : MainScreen(route = "MAIN")

}