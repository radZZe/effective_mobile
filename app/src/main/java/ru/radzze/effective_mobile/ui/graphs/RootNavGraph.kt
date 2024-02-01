package ru.radzze.effective_mobile.ui.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.radzze.effective_mobile.ui.BottomNavScreen
import ru.radzze.effective_mobile.ui.HomeScreen
import ru.radzze.effective_mobile.ui.catalog.CatalogScreen
import ru.radzze.effective_mobile.ui.main.MainScreen

@Composable
fun RootNavigationGraph(navController: NavHostController,isAuth:Boolean) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = if(isAuth) Graph.MAIN else Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController)
        catalogNavGraph(navController=navController)
        composable(route = Graph.MAIN) {
            HomeScreen(isAuth = isAuth)
        }
        composable(route = BottomNavScreen.Catalog.route) {
            CatalogScreen()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val ACCOUNT = "account_graph"
    const val CATALOG = "catalog_graph"
    const val MAIN = "main_graph"
}